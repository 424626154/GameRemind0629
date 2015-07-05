package com.module;


import com.qianghuai.gr.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.TextView;

/**
 * 提醒弹出
 * @author g
 *
 */
public class RemindAlert extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
//		long _id = getIntent().getExtras().getLong("_id", 0);
//		String type = getIntent().getExtras().getString("type");
//		new AlertDialog.Builder(RemindAlert.this)
//			.setTitle(type)
//			.setMessage(_id+"")
//			.setNegativeButton("关掉它", new DialogInterface.OnClickListener() {
//				@Override
//				public void onClick(DialogInterface dialog, int which) {
//					// TODO Auto-generated method stub
//					RemindAlert.this.finish();
//				}
//			}).show();
		setContentView(R.layout.remind_alert_layout);
		long _id = getIntent().getExtras().getLong("_id", 0);
		String remarks = getIntent().getExtras().getString("remarks");
		TextView title = (TextView)findViewById(R.id.title);
		title.setText(_id+remarks);
		MyApplication.getInstance().getNotificationManager().cancel((int)_id);
//		MyApplication.getInstance().getNotificationManager().cancelAll();
//		finish();
	}
	
}
