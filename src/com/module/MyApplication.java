package com.module;


import net.youmi.android.AdManager;
import net.youmi.android.spot.SpotManager;

import com.data.MyData;
import com.data.Remind;
import com.db.DBHelper;
import com.db.SqlLiteHelper;
import com.qianghuai.gr.R;
import com.umeng.fb.FeedbackAgent;
import com.umeng.update.UmengUpdateAgent;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

public class MyApplication extends Application{
	public static DBHelper dbHelper;
	public MyData myData = null;
	private static MyApplication mApplication;
	public synchronized static MyApplication getInstance() {
		return mApplication;
	}
	private NotificationManager mNotificationManager;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		mApplication = this;
		dbHelper = DBHelper.getInstance(getApplicationContext());
		myData = new MyData();
		//���˷���
		FeedbackAgent agent = new FeedbackAgent(this);
		agent.sync();
		//���˸���
		UmengUpdateAgent.update(this);
		//�������ݼ���
		SpotManager.getInstance(this).loadSpotAds();
		//���׹�����
		SpotManager.getInstance(this).setSpotOrientation(
	            SpotManager.ORIENTATION_LANDSCAPE);
		
		AdManager.getInstance(this).init("cfdbdd2786ea88ea", "d8edde7d10dd0073", true);
	}
	
	public NotificationManager getNotificationManager() {
		if (mNotificationManager == null)
			mNotificationManager = (NotificationManager) getSystemService(android.content.Context.NOTIFICATION_SERVICE);
		return mNotificationManager;
	}
	/**
	    * ��ͨ��֪ͨ
	   *���֪ͨ����ת����һ��activity
	    */
	public void showNotify(Remind remind)
	{
//		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
//				this).setSmallIcon(R.drawable.logo_smali)//����ͼ��
//				.setContentTitle(remind.appLabel)//���ñ���
//				.setContentText(remind.pkgName);//��������
//		/*����PendingIntent�����û����֪ͨ��ת����һ�����棬���˳��ý��棬ֱ�ӻص�HOME*/
//		Intent resultIntent = new Intent(this, AlarmAlert.class);
//		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
//		stackBuilder.addParentStack(AlarmAlert.class);
//		stackBuilder.addNextIntent(resultIntent);
//		PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,
//				PendingIntent.FLAG_UPDATE_CURRENT);
//		mBuilder.setContentIntent(resultPendingIntent);//����PendingIntent
//
//		//����NotificationManager ����
//		NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//		Notification notification = mBuilder.build();//����Notification����
//		notification.flags = Notification.FLAG_AUTO_CANCEL;//������Զ��ر�֪ͨ
//		mNotificationManager.notify(1,notification);
		
		//��Ϣ֪ͨ��
        //����NotificationManager
        NotificationManager mNotificationManager = getNotificationManager();
        //����֪ͨ��չ�ֵ�������Ϣ
        int icon = R.drawable.logo_smali;
        CharSequence tickerText = remind.appLabel;
        long when = System.currentTimeMillis();
        Notification notification = new Notification(icon, tickerText, when);
         
        //��������֪ͨ��ʱҪչ�ֵ�������Ϣ
        Context context = getApplicationContext();
        CharSequence contentTitle = remind.appLabel;
        CharSequence contentText = remind.remarks;
        Intent notificationIntent = new Intent(this, RemindAlert.class);
        Bundle bundle = new Bundle(); 
        bundle.putLong("_id",remind._id); 
        bundle.putString("remarks",remind.remarks); 
        notificationIntent.putExtras(bundle);
        PendingIntent contentIntent = PendingIntent.getActivity(this, (int)remind._id,
                notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setLatestEventInfo(context, contentTitle, contentText,
                contentIntent);
        //��mNotificationManager��notify����֪ͨ�û����ɱ�������Ϣ֪ͨ
        mNotificationManager.notify((int) remind._id, notification);
	}
}
