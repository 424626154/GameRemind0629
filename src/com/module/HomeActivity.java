package com.module;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import net.youmi.android.banner.AdSize;
import net.youmi.android.banner.AdView;
import net.youmi.android.banner.AdViewListener;
import net.youmi.android.spot.SpotDialogListener;
import net.youmi.android.spot.SpotManager;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.base.BaseActivity;
import com.data.AppInfo;
import com.data.Remind;
import com.qianghuai.gr.R;
import com.umeng.fb.FeedbackAgent;
import com.umeng.update.UmengUpdateAgent;
import com.umeng.update.UmengUpdateListener;
import com.umeng.update.UpdateResponse;
import com.umeng.update.UpdateStatus;
/**
 * 主页
 * @author g
 *
 */
public class HomeActivity extends BaseActivity{

	private RelativeLayout more_layout = null;
	private ListView listview = null;
	private HomeAdapter adapter = null;
	public PopupWindow popupWindow = null;
	private Button add = null;
	private Button del = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_layout);
		SpotManager.getInstance(this).showSpotAds(this, new SpotDialogListener() {
		    @Override
		    public void onShowSuccess() {
		        Log.i("Youmi", "onShowSuccess");
		    }

		    @Override
		    public void onShowFailed() {
		        Log.i("Youmi", "onShowFailed");
		    }

		    @Override
		    public void onSpotClosed() {
		        Log.e("sdkDemo", "closed");
		    }
		});
		
		initView();
		// 5秒后发送广播，只发送一次
//		AlarmManager alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//		Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
//		AppInfo[] appInfos = MyApplication.getInstance().dbHelper.queryAllAppInfos();
//		if(appInfos != null){
//			intent.putExtra("lebel",appInfos[0].appLabel);
//			intent.putExtra("pkgname",appInfos[0].pkgName);
//		}
//		int requestCode = 0;
//		PendingIntent pendIntent = PendingIntent.getBroadcast(getApplicationContext(),
//				requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//		long firstTime = SystemClock.elapsedRealtime(); // 开机之后到现在的运行时间(包括睡眠时间)  
//		long systemTime = System.currentTimeMillis();  
//
//		Calendar calendar = Calendar.getInstance();  
//		calendar.setTimeInMillis(System.currentTimeMillis());  
//		// 这里时区需要设置一下，不然会有8个小时的时间差  
////		calendar.setTimeZone(TimeZone.getTimeZone("GMT+8"));  
//		calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE)+2);  
//		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY));  
//		calendar.set(Calendar.SECOND, 0);  
//		calendar.set(Calendar.MILLISECOND, 0);  
//		// 选择的定时时间  
//		long selectTime = calendar.getTimeInMillis();  
//		// 如果当前时间大于设置的时间，那么就从第二天的设定时间开始  
//		//		if(systemTime > selectTime) {  
//		//		Toast.makeText(HomeActivity.this,"设置的时间小于当前时间", Toast.LENGTH_SHORT).show();  
//		//		calendar.add(Calendar.DAY_OF_MONTH, 1);  
//		//		selectTime = calendar.getTimeInMillis();  
//		//		}  
//		// 计算现在时间到设定时间的时间差  
//		long time = selectTime - systemTime;  
//		firstTime += time;  
//		alarmMgr.set(AlarmManager.ELAPSED_REALTIME,calendar.getTimeInMillis(), pendIntent);

		//		// 5秒后发送广播，然后每个10秒重复发广播。广播都是直接发到AlarmReceiver的
		//		AlarmManager alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		//		Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
		//		int requestCode = 0;
		//		PendingIntent pendIntent = PendingIntent.getBroadcast(getApplicationContext(),
		//				requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		//		int triggerAtTime = SystemClock.elapsedRealtime() + 5 * 1000;
		//		int interval = 10 * 1000;
		//		alarmMgr.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, interval, pendIntent);
		//
		//		// 与上面的intent匹配（filterEquals(intent)）的闹钟会被取消
		//		AlarmManager alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		//		Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
		//		PendingIntent pendIntent = PendingIntent.getBroadcast(getApplicationContext(),
		//				0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		//		alarmMgr.cancel(pendIntent);
		
//		Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(System.currentTimeMillis());
//		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY));
//		calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE));
//		calendar.set(Calendar.SECOND, 0);
//		calendar.set(Calendar.MILLISECOND, 0);
//		
//		Intent intent = new Intent(HomeActivity.this,OneShotAlarm.class);
//		intent.putExtra("_id",13);
//		PendingIntent pdIntent= PendingIntent.getBroadcast(HomeActivity.this, (int) 13, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//		
//		AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
//		/**
//		 * AlarmManager.RTC_WAKEUP 在系统休眠的时候同样运行
//		 * 以set()设置的PendingIntent只会运行一次
//		 */
//		am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pdIntent);
//		Remind remind = new Remind();
//		remind._id = 1;
//		remind.appLabel = "label";
//		remind.remarks = "remarks";
//		MyApplication.getInstance().showNotify(remind);
		
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		AppInfo[] appInfos = MyApplication.getInstance().dbHelper.queryAllAppInfos();
		List<AppInfo> list = new ArrayList<AppInfo>();
		if(appInfos != null){
			for(int i = 0 ; i < appInfos.length ; i ++){
				appInfos[i].num = 0;
				Remind[] reminds = MyApplication.getInstance().dbHelper.queryAllReminds(appInfos[i].pkgName);
				if(reminds != null){
					appInfos[i].num = reminds.length;
				}
				list.add(appInfos[i]);
			}
		}

		adapter = new HomeAdapter(HomeActivity.this,list);
		listview.setAdapter(adapter);
	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		// 如果不调用此方法，则按home键的时候会出现图标无法显示的情况。
		SpotManager.getInstance(this).onStop();
		super.onStop();
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		SpotManager.getInstance(this).onDestroy();
		super.onDestroy();
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
	    // 如果有需要，可以点击后退关闭插播广告。
	    if (!SpotManager.getInstance(this).disMiss()) {
	        // 弹出退出窗口，可以使用自定义退屏弹出和回退动画,参照demo,若不使用动画，传入-1
	        super.onBackPressed();
	    }
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	     if (keyCode == KeyEvent.KEYCODE_BACK) {  
	            moveTaskToBack(false);  
	            return true;  
	        }  
	        return super.onKeyDown(keyCode, event);  
	}
	
	public void initView(){
		more_layout = (RelativeLayout)findViewById(R.id.more_layout);
		more_layout.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//				Intent intent = new Intent();
				//				intent.setClass(HomeActivity.this, CreateRemindActivity.class);
				//				startActivity(intent);
				showMore();
			}
		});

		add = (Button)findViewById(R.id.add);
		del = (Button)findViewById(R.id.del);
		add.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(HomeActivity.this, AppListActivity.class);
				startActivity(intent);
			}
		});
		del.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(HomeActivity.this, DelRemindActivity.class);
				startActivity(intent);
			}
		});


		listview = (ListView)findViewById(R.id.listview);
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				AppInfo appInfo = (AppInfo)adapter.getItem(position);
				MyApplication.getInstance().myData.remind.appLabel = appInfo.appLabel;
				MyApplication.getInstance().myData.remind.appIcon = appInfo.appIcon;
				MyApplication.getInstance().myData.remind.pkgName = appInfo.pkgName;
				Intent intent = new Intent();
				intent.setClass(HomeActivity.this, RemindListActivity.class);
				startActivity(intent);
			}
		});
		
		// 实例化广告条
		AdView adView = new AdView(this, AdSize.FIT_SCREEN);

		// 获取要嵌入广告条的布局
		LinearLayout adLayout=(LinearLayout)findViewById(R.id.adLayout);

		// 将广告条加入到布局中
		adLayout.addView(adView);
		adView.setAdListener(new AdViewListener() {

		    @Override
		    public void onSwitchedAd(AdView adView) {
		        // 切换广告并展示
		    	System.out.println("切换广告并展示");
		    }

		    @Override
		    public void onReceivedAd(AdView adView) {
		        // 请求广告成功
		    	System.out.println("请求广告成功");
		    }

		    @Override
		    public void onFailedToReceivedAd(AdView adView) {
		        // 请求广告失败
		    	System.out.println("请求广告失败");
		    }
		});
	}
	class HomeAdapter extends BaseAdapter{
		private List<AppInfo> mlistAppInfo = null;
		LayoutInflater infater = null;
		public HomeAdapter(Context context,  List<AppInfo> apps) {
			infater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			mlistAppInfo = apps ;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			System.out.println("size" + mlistAppInfo.size());
			return mlistAppInfo.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return mlistAppInfo.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertview, ViewGroup arg2) {
			System.out.println("getView at " + position);
			View view = null;
			ViewHolder holder = null;
			if (convertview == null || convertview.getTag() == null) {
				view = infater.inflate(R.layout.home_list_item, null);
				holder = new ViewHolder(view);
				view.setTag(holder);
			}else{
				view = convertview ;
				holder = (ViewHolder) convertview.getTag() ;
			}
			final AppInfo appInfo = (AppInfo) getItem(position);
			holder.icon.setImageDrawable(appInfo.getAppIcon());
			holder.appname.setText(appInfo.getAppLabel());
			holder.icon_layout.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					MyApplication.getInstance().myData.remind.appLabel = appInfo.appLabel;
					MyApplication.getInstance().myData.remind.appIcon = appInfo.appIcon;
					MyApplication.getInstance().myData.remind.pkgName = appInfo.pkgName;
					Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage(appInfo.getPkgName());  
					startActivity(LaunchIntent);  
				}
			});
			holder.setting_layout.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					MyApplication.getInstance().myData.remind.appLabel = appInfo.appLabel;
					MyApplication.getInstance().myData.remind.appIcon = appInfo.appIcon;
					MyApplication.getInstance().myData.remind.pkgName = appInfo.pkgName;
					Intent intent = new Intent();
					intent.setClass(HomeActivity.this, RemindListActivity.class);
					startActivity(intent);
				}
			});
			if(appInfo.num > 0){
				holder.num.setText("x"+appInfo.num);
			}else{
				holder.num.setText("无");
			}
			return view;
		}

		class ViewHolder {
			ImageView icon;
			TextView appname;
			FrameLayout icon_layout;
			TextView num;
			LinearLayout setting_layout;
			public ViewHolder(View view) {
				this.icon = (ImageView) view.findViewById(R.id.icon);
				this.appname = (TextView) view.findViewById(R.id.appname);
				this.icon_layout = (FrameLayout) view.findViewById(R.id.icon_layout);
				this.setting_layout = (LinearLayout)view.findViewById(R.id.setting_layout);
				this.num = (TextView)view.findViewById(R.id.num);
			}
		}	
	}

	public void showMore(){
		initPopWindow();
		popupWindow.showAtLocation(findViewById(R.id.layout),Gravity.TOP | Gravity.LEFT,
				0,0);
	}
	/** 
	 * 初始化popWindow
	 * */
	private void initPopWindow() {
		LayoutInflater layoutInflater = LayoutInflater.from(this); 
		View popView = layoutInflater.inflate(R.layout.popup_remid, null);
		popupWindow = new PopupWindow(popView, WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.MATCH_PARENT);
		popupWindow.setBackgroundDrawable(new ColorDrawable(0));
		//设置popwindow出现和消失动画
		//        popupWindow.setAnimationStyle(R.style.PopMenuAnimation);
		TextView feedback = (TextView) popView.findViewById(R.id.feedback);
		TextView update = (TextView) popView.findViewById(R.id.update);
		TextView sign_out = (TextView) popView.findViewById(R.id.sign_out);
		feedback.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (popupWindow != null && popupWindow.isShowing()) {
					popupWindow.dismiss();
				}
				FeedbackAgent agent = new FeedbackAgent(HomeActivity.this);
				agent.removeWelcomeInfo();
				agent.startFeedbackActivity();
			}
		});
		update.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (popupWindow != null && popupWindow.isShowing()) {
					popupWindow.dismiss();
				}
                //请在调用update,forceUpdate,silentUpdate函数之前设置推广id
				UmengUpdateAgent.setUpdateAutoPopup(false);
				UmengUpdateAgent.setUpdateListener(new UmengUpdateListener() {
				    @Override
				    public void onUpdateReturned(int updateStatus,UpdateResponse updateInfo) {
				        switch (updateStatus) {
				        case UpdateStatus.Yes: // has update
				            UmengUpdateAgent.showUpdateDialog(HomeActivity.this, updateInfo);
				            break;
				        case UpdateStatus.No: // has no update
				            Toast.makeText(HomeActivity.this, "没有更新", Toast.LENGTH_SHORT).show();
				            break;
				        case UpdateStatus.NoneWifi: // none wifi
				            Toast.makeText(HomeActivity.this, "没有wifi连接， 只在wifi下更新", Toast.LENGTH_SHORT).show();
				            break;
				        case UpdateStatus.Timeout: // time out
				            Toast.makeText(HomeActivity.this, "超时", Toast.LENGTH_SHORT).show();
				            break;
				        }
				    }
				});
				UmengUpdateAgent.update(HomeActivity.this);
			}
		});
		sign_out.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		RelativeLayout layMenu = (RelativeLayout) popView.findViewById(R.id.pop_layout);
		layMenu.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (popupWindow != null && popupWindow.isShowing()) {
					popupWindow.dismiss();
				}
				return false;
			}
		});
	}

}
