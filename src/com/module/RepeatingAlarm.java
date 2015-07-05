package com.module;

import com.data.Remind;
import com.qianghuai.gr.R;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.widget.Toast;
/**
 * �ظ�����
 * @author g
 *
 */
public class RepeatingAlarm extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle1 = intent.getExtras();
		long _id = bundle1.getLong("_id", 0);
//		Intent i = new Intent(context, AlarmAlert.class);
//		Bundle bundle = new Bundle();
//		bundle.putLong("_id",_id);
//		bundle.putString("type", "RepeatingAlarm");
//		i.putExtras(bundle);
//		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//		context.startActivity(i);
		Remind remind = MyApplication.getInstance().dbHelper.queryRemind(_id);
		MyApplication.getInstance().showNotify(remind);
//		NotificationCompat.Builder mBuilder =
//		        new NotificationCompat.Builder(context)
//		        .setSmallIcon(R.drawable.logo_smali)
//		        .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.logo_smali))
//		        .setWhen(System.currentTimeMillis())//����ʱ�䷢��ʱ��
//		        .setAutoCancel(true)//���ÿ������
//		        .setTicker(remind.appLabel)//����״̬������ʾ����Ϣ
//		        .setContentTitle(remind.appLabel)
//		        .setContentText(remind.pkgName);
//			mBuilder.setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);
//		
//		intent = new Intent(context, AlarmAlert.class);
//		Bundle bundle = new Bundle();
//		bundle.putLong("_id",_id);
//		bundle.putString("type", "RepeatingAlarm");
//		intent.putExtras(bundle);
//		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//		TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
//		stackBuilder.addParentStack(HomeActivity.class);
//		stackBuilder.addNextIntent(intent);
//		PendingIntent resultPendingIntent =
//		        stackBuilder.getPendingIntent(
//		            0,
//		            PendingIntent.FLAG_UPDATE_CURRENT
//		        );
//		mBuilder.setContentIntent(resultPendingIntent);
//		NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//		// mId allows you to update the notification later on.
//		mNotificationManager.notify(1, mBuilder.build());
	}

}
