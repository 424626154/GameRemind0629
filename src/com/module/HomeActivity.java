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
 * ��ҳ
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
		// 5����͹㲥��ֻ����һ��
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
//		long firstTime = SystemClock.elapsedRealtime(); // ����֮�����ڵ�����ʱ��(����˯��ʱ��)  
//		long systemTime = System.currentTimeMillis();  
//
//		Calendar calendar = Calendar.getInstance();  
//		calendar.setTimeInMillis(System.currentTimeMillis());  
//		// ����ʱ����Ҫ����һ�£���Ȼ����8��Сʱ��ʱ���  
////		calendar.setTimeZone(TimeZone.getTimeZone("GMT+8"));  
//		calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE)+2);  
//		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY));  
//		calendar.set(Calendar.SECOND, 0);  
//		calendar.set(Calendar.MILLISECOND, 0);  
//		// ѡ��Ķ�ʱʱ��  
//		long selectTime = calendar.getTimeInMillis();  
//		// �����ǰʱ��������õ�ʱ�䣬��ô�ʹӵڶ�����趨ʱ�俪ʼ  
//		//		if(systemTime > selectTime) {  
//		//		Toast.makeText(HomeActivity.this,"���õ�ʱ��С�ڵ�ǰʱ��", Toast.LENGTH_SHORT).show();  
//		//		calendar.add(Calendar.DAY_OF_MONTH, 1);  
//		//		selectTime = calendar.getTimeInMillis();  
//		//		}  
//		// ��������ʱ�䵽�趨ʱ���ʱ���  
//		long time = selectTime - systemTime;  
//		firstTime += time;  
//		alarmMgr.set(AlarmManager.ELAPSED_REALTIME,calendar.getTimeInMillis(), pendIntent);

		//		// 5����͹㲥��Ȼ��ÿ��10���ظ����㲥���㲥����ֱ�ӷ���AlarmReceiver��
		//		AlarmManager alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		//		Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
		//		int requestCode = 0;
		//		PendingIntent pendIntent = PendingIntent.getBroadcast(getApplicationContext(),
		//				requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		//		int triggerAtTime = SystemClock.elapsedRealtime() + 5 * 1000;
		//		int interval = 10 * 1000;
		//		alarmMgr.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, interval, pendIntent);
		//
		//		// �������intentƥ�䣨filterEquals(intent)�������ӻᱻȡ��
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
//		 * AlarmManager.RTC_WAKEUP ��ϵͳ���ߵ�ʱ��ͬ������
//		 * ��set()���õ�PendingIntentֻ������һ��
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
		// ��������ô˷�������home����ʱ������ͼ���޷���ʾ�������
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
	    // �������Ҫ�����Ե�����˹رղ岥��档
	    if (!SpotManager.getInstance(this).disMiss()) {
	        // �����˳����ڣ�����ʹ���Զ������������ͻ��˶���,����demo,����ʹ�ö���������-1
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
		
		// ʵ���������
		AdView adView = new AdView(this, AdSize.FIT_SCREEN);

		// ��ȡҪǶ�������Ĳ���
		LinearLayout adLayout=(LinearLayout)findViewById(R.id.adLayout);

		// ����������뵽������
		adLayout.addView(adView);
		adView.setAdListener(new AdViewListener() {

		    @Override
		    public void onSwitchedAd(AdView adView) {
		        // �л���沢չʾ
		    	System.out.println("�л���沢չʾ");
		    }

		    @Override
		    public void onReceivedAd(AdView adView) {
		        // ������ɹ�
		    	System.out.println("������ɹ�");
		    }

		    @Override
		    public void onFailedToReceivedAd(AdView adView) {
		        // ������ʧ��
		    	System.out.println("������ʧ��");
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
				holder.num.setText("��");
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
	 * ��ʼ��popWindow
	 * */
	private void initPopWindow() {
		LayoutInflater layoutInflater = LayoutInflater.from(this); 
		View popView = layoutInflater.inflate(R.layout.popup_remid, null);
		popupWindow = new PopupWindow(popView, WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.MATCH_PARENT);
		popupWindow.setBackgroundDrawable(new ColorDrawable(0));
		//����popwindow���ֺ���ʧ����
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
                //���ڵ���update,forceUpdate,silentUpdate����֮ǰ�����ƹ�id
				UmengUpdateAgent.setUpdateAutoPopup(false);
				UmengUpdateAgent.setUpdateListener(new UmengUpdateListener() {
				    @Override
				    public void onUpdateReturned(int updateStatus,UpdateResponse updateInfo) {
				        switch (updateStatus) {
				        case UpdateStatus.Yes: // has update
				            UmengUpdateAgent.showUpdateDialog(HomeActivity.this, updateInfo);
				            break;
				        case UpdateStatus.No: // has no update
				            Toast.makeText(HomeActivity.this, "û�и���", Toast.LENGTH_SHORT).show();
				            break;
				        case UpdateStatus.NoneWifi: // none wifi
				            Toast.makeText(HomeActivity.this, "û��wifi���ӣ� ֻ��wifi�¸���", Toast.LENGTH_SHORT).show();
				            break;
				        case UpdateStatus.Timeout: // time out
				            Toast.makeText(HomeActivity.this, "��ʱ", Toast.LENGTH_SHORT).show();
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
