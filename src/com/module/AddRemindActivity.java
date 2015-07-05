package com.module;

import java.util.Calendar;

import org.json.JSONArray;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.base.BaseActivity;
import com.data.DataUtil;
import com.data.Remind;
import com.qianghuai.gr.R;
import com.widget.time.NumericWheelAdapter;
import com.widget.time.OnWheelChangedListener;
import com.widget.time.OnWheelScrollListener;
import com.widget.time.WheelView;
/**
 *添加提醒
 */
public class AddRemindActivity extends BaseActivity{
	private TextView title ;
	private RelativeLayout back_layout;
	private RelativeLayout ok_layout ;
	private TextView ok_tv = null;
	private ImageView icon = null;
	private TextView appname = null;
	private WheelView wv_hours;
	private WheelView wv_mins;
	private int hour, minute;
	private RelativeLayout repeat_layout;
	private TextView repeat_tv ;
	private TextView repeat_info_tv ;
	private int[] weeks = null;
	
	private RelativeLayout remarks_layout ;
	private TextView remarks_info_tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_remind_layout);
		initView();
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if(MyApplication.getInstance().myData.remind != null){
			String repeat_info = "";
			int num = 0;
			if(MyApplication.getInstance().myData.remind.b_week != null){
				for(int i = 0 ; i < MyApplication.getInstance().myData.remind.b_week.length ; i ++){
					if(MyApplication.getInstance().myData.remind.b_week[i]){
						repeat_info += "星期"+(i+1);
						num++;
					}
				}
			}
			if(num == 0){
				repeat_info = "永不";
			}
			if(num == 7){
				repeat_info = "每天";
			}
			repeat_info_tv.setText(repeat_info);
			remarks_info_tv.setText(MyApplication.getInstance().myData.remind.remarks);
		}

	}
	public void initView(){
		title = (TextView)findViewById(R.id.title);
		title.setText("添加提醒");
		back_layout = (RelativeLayout)findViewById(R.id.back_layout);
		back_layout.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		ok_layout = (RelativeLayout)findViewById(R.id.ok_layout);
		ok_layout.setVisibility(View.VISIBLE);
		ok_layout.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				JSONArray jsonArray = new JSONArray(); 
				for(int i = 0 ; i < MyApplication.getInstance().myData.remind.b_week.length ; i ++){
					jsonArray.put(MyApplication.getInstance().myData.remind.b_week[i]);
				}
				MyApplication.getInstance().myData.remind.week_json = jsonArray.toString();
				MyApplication.getInstance().myData.remind.on_off = 1;
				long _id = MyApplication.getInstance().dbHelper.insetRemind(MyApplication.getInstance().myData.remind);
				addRemind(_id,MyApplication.getInstance().myData.remind);
				finish();
				
			}
		});
		ok_tv = (TextView)findViewById(R.id.ok_tv);
		ok_tv.setVisibility(View.VISIBLE);
		ok_tv.setText("保存");
		icon = (ImageView)findViewById(R.id.icon);
		appname = (TextView)findViewById(R.id.appname);
		if(MyApplication.getInstance().myData.remind != null){
			icon.setImageDrawable(MyApplication.getInstance().myData.remind.appIcon);
			appname.setText(MyApplication.getInstance().myData.remind.appLabel);
		}
		int textSize = DataUtil.adjustFontSize(getWindow().getWindowManager());
		Calendar mCalendar = Calendar.getInstance();
    	hour = mCalendar.get(Calendar.HOUR_OF_DAY)+1;
		minute = mCalendar.get(Calendar.MINUTE) + 1;
		MyApplication.getInstance().myData.remind.hour = hour;
		MyApplication.getInstance().myData.remind.minute = minute;
		wv_hours = (WheelView)findViewById(R.id.hour);
		wv_mins = (WheelView)findViewById(R.id.min);
		wv_hours.setVisibility(View.VISIBLE);
		wv_mins.setVisibility(View.VISIBLE);
		
		wv_hours.setAdapter(new NumericWheelAdapter(0, 23));
		wv_hours.setCyclic(true);
		wv_hours.setCurrentItem(hour);
		
		
		wv_mins.setAdapter(new NumericWheelAdapter(0, 59));
		wv_mins.setCyclic(true);
		wv_mins.setCurrentItem(minute);
		
		wv_hours.addChangingListener(wheelListener_hours);
		wv_mins.addChangingListener(wheelListener_mins);
		wv_hours.TEXT_SIZE = textSize;
		wv_mins.TEXT_SIZE = textSize;
		wv_hours.addScrollingListener(onWheelScrollListener);
		wv_mins.addScrollingListener(onWheelScrollListener);
		
		repeat_layout = (RelativeLayout)findViewById(R.id.repeat_layout);
		repeat_layout.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(AddRemindActivity.this, RepeatRemindActivity.class);
				startActivity(intent);
			}
		});
		repeat_tv = (TextView)findViewById(R.id.repeat_tv);
		repeat_info_tv = (TextView)findViewById(R.id.repeat_info_tv);
		repeat_info_tv.setText("");
		
		remarks_layout = (RelativeLayout)findViewById(R.id.remarks_layout);
		remarks_layout.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(AddRemindActivity.this, RemarksActivity.class);
				startActivity(intent);
			}
		});
		remarks_info_tv = (TextView)findViewById(R.id.remarks_info_tv);
		
		if(MyApplication.getInstance().myData.remind != null){
			String repeat_info = "";
			if(MyApplication.getInstance().myData.remind.b_week != null){
				for(int i = 0 ; i < MyApplication.getInstance().myData.remind.b_week.length ; i ++){
					MyApplication.getInstance().myData.remind.b_week[i] = false;
				}
			}
			MyApplication.getInstance().myData.remind.remarks = "";
		}
	}
	
	OnWheelScrollListener onWheelScrollListener = new OnWheelScrollListener() {

		@Override
		public void onScrollingStarted(WheelView wheel) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onScrollingFinished(WheelView wheel) {
			// TODO Auto-generated method stub
//			age.setText((mCalendar.get(Calendar.YEAR)
//					- wv_year.getCurrentItem() - START_YEAR)
//					+ "岁");
//			curr_year = wv_year.getCurrentItem() + START_YEAR;
//			curr_month = wv_month.getCurrentItem() + 1;
//			curr_day = wv_day.getCurrentItem() + 1;
//			birthday.setText(getConstellation(curr_month, curr_day));
			MyApplication.getInstance().myData.remind.hour = wv_hours.getCurrentItem()-1;
			MyApplication.getInstance().myData.remind.minute = wv_mins.getCurrentItem()-1;
			
		}
	};
	
	// 添加"年"监听
		OnWheelChangedListener wheelListener_hours = new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
//				int year_num = newValue + START_YEAR;
//				// 判断大小月及是否闰年,用来确定"日"的数据
//				if (list_big
//						.contains(String.valueOf(wv_month.getCurrentItem() + 1))) {
//					wv_day.setAdapter(new NumericWheelAdapter(1, 31));
//				} else if (list_little.contains(String.valueOf(wv_month
//						.getCurrentItem() + 1))) {
//					wv_day.setAdapter(new NumericWheelAdapter(1, 30));
//				} else {
//					if ((year_num % 4 == 0 && year_num % 100 != 0)
//							|| year_num % 400 == 0)
//						wv_day.setAdapter(new NumericWheelAdapter(1, 29));
//					else
//						wv_day.setAdapter(new NumericWheelAdapter(1, 28));
//				}
			}
		};
		// 添加"月"监听
		OnWheelChangedListener wheelListener_mins = new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
//				int month_num = newValue + 1;
//				// 判断大小月及是否闰年,用来确定"日"的数据
//				if (list_big.contains(String.valueOf(month_num))) {
//					wv_day.setAdapter(new NumericWheelAdapter(1, 31));
//				} else if (list_little.contains(String.valueOf(month_num))) {
//					wv_day.setAdapter(new NumericWheelAdapter(1, 30));
//				} else {
//					if (((wv_year.getCurrentItem() + START_YEAR) % 4 == 0 && (wv_year
//							.getCurrentItem() + START_YEAR) % 100 != 0)
//							|| (wv_year.getCurrentItem() + START_YEAR) % 400 == 0)
//						wv_day.setAdapter(new NumericWheelAdapter(1, 29));
//					else
//						wv_day.setAdapter(new NumericWheelAdapter(1, 28));
//				}
			}
		};
		public long repeat_time = 24*60*60*1000;
		public void addRemind(long _id,Remind r){
			long requestCode = _id;
			boolean b_repeat = false;
			if(r.b_week != null){
				for(int i = 0 ; i < r.b_week.length ; i ++){
					if(r.b_week[i]){
						b_repeat = true;
					}
				}
			}
//			Calendar.YEAR——年份
//
//			Calendar.MONTH——月份
//
//			Calendar.DATE——日期
//
//			Calendar.DAY_OF_MONTH——日期，和上面的字段意义完全相同
//
//			Calendar.HOUR——12小时制的小时
//
//			Calendar.HOUR_OF_DAY——24小时制的小时
//
//			Calendar.MINUTE——分钟
//
//			Calendar.SECOND——秒
//
//			Calendar.DAY_OF_WEEK——星期几
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
			calendar.set(Calendar.HOUR_OF_DAY, hour-1);
			calendar.set(Calendar.MINUTE, minute);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			//设置重复
			if(b_repeat){
	            // When the alarm goes off, we want to broadcast an Intent to our
	            // BroadcastReceiver. Here we make an Intent with an explicit class
	            // name to have our own receiver (which has been published in
	            // AndroidManifest.xml) instantiated and called, and then create an
	            // IntentSender to have the intent executed as a broadcast.
	            // Note that unlike above, this IntentSender is configured to
	            // allow itself to be sent multiple times.
	            Intent intent = new Intent(AddRemindActivity.this,
	                    RepeatingAlarm.class);
	            intent.putExtra("_id",_id);
	            PendingIntent sender = PendingIntent.getBroadcast(
	            		AddRemindActivity.this, (int) requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);

	            // We want the alarm to go off 10 seconds from now.
	            // Schedule the alarm!
	            AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
	            am.setRepeating(AlarmManager.RTC_WAKEUP,
	                    calendar.getTimeInMillis(), repeat_time, sender);
			//设置单次	
			}else{
	            // When the alarm goes off, we want to broadcast an Intent to our
	            // BroadcastReceiver. Here we make an Intent with an explicit class
	            // name to have our own receiver (which has been published in
	            // AndroidManifest.xml) instantiated and called, and then create an
	            // IntentSender to have the intent executed as a broadcast.
//	            Intent intent = new Intent(AddRemindActivity.this, OneShotAlarm.class);
//	            PendingIntent sender = PendingIntent.getBroadcast(
//	            		AddRemindActivity.this, 0, intent, 0);
//
//	            // We want the alarm to go off 10 seconds from now.
//	            // Schedule the alarm!
//	            AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
//	            am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
				
				//指定闹钟时间到了响起
				Intent intent = new Intent(AddRemindActivity.this,OneShotAlarm.class);
				intent.putExtra("_id",_id);
				PendingIntent pdIntent= PendingIntent.getBroadcast(AddRemindActivity.this, (int) requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
				
				AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
				/**
				 * AlarmManager.RTC_WAKEUP 在系统休眠的时候同样运行
				 * 以set()设置的PendingIntent只会运行一次
				 */
				am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pdIntent);
				
			}
		}

}
