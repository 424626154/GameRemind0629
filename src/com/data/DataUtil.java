package com.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.view.WindowManager;

public class DataUtil {
	public static final int FILTER_ALL_APP = 0; // ����Ӧ�ó���
	public static final int FILTER_SYSTEM_APP = 1; // ϵͳ����
	public static final int FILTER_THIRD_APP = 2; // ������Ӧ�ó���
	public static final int FILTER_SDCARD_APP = 3; // ��װ��SDCard��Ӧ�ó���
	// ���ݲ�ѯ��������ѯ�ض���ApplicationInfo
	public static List<AppInfo> queryFilterAppInfo(PackageManager pm,int filter) {
		// ��ѯ�����Ѿ���װ��Ӧ�ó���
		List<ApplicationInfo> listAppcations = pm
				.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
		Collections.sort(listAppcations,
				new ApplicationInfo.DisplayNameComparator(pm));// ����
		List<AppInfo> appInfos = new ArrayList<AppInfo>(); // ������˲鵽��AppInfo
		// ��������������
		switch (filter) {
		case FILTER_ALL_APP: // ����Ӧ�ó���
			appInfos.clear();
			for (ApplicationInfo app : listAppcations) {
				appInfos.add(getAppInfo(pm,app));
			}
			return appInfos;
		case FILTER_SYSTEM_APP: // ϵͳ����
			appInfos.clear();
			for (ApplicationInfo app : listAppcations) {
				if ((app.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
					appInfos.add(getAppInfo(pm,app));
				}
			}
			return appInfos;
		case FILTER_THIRD_APP: // ������Ӧ�ó���
			appInfos.clear();
			for (ApplicationInfo app : listAppcations) {
				if ((app.flags & ApplicationInfo.FLAG_SYSTEM) <= 0) {
					appInfos.add(getAppInfo(pm,app));
				}
			}
			break;
		case FILTER_SDCARD_APP: // ��װ��SDCard��Ӧ�ó���
			appInfos.clear();
			for (ApplicationInfo app : listAppcations) {
				if ((app.flags & ApplicationInfo.FLAG_EXTERNAL_STORAGE) != 0) {
					appInfos.add(getAppInfo(pm,app));
				}
			}
			return appInfos;
		default:
			return null;
		}
		return appInfos;
	}
	// ����һ��AppInfo���� ������ֵ
	private static AppInfo getAppInfo(PackageManager pm ,ApplicationInfo app) {
		AppInfo appInfo = new AppInfo();
		appInfo.setAppLabel((String) app.loadLabel(pm));
		appInfo.setAppIcon(app.loadIcon(pm));
		appInfo.setPkgName(app.packageName);
		return appInfo;
	}
	
	
	public static int adjustFontSize(WindowManager windowmanager) {
		int screenWidth = windowmanager.getDefaultDisplay().getWidth();
		int screenHeight = windowmanager.getDefaultDisplay().getHeight();
		if (screenWidth <= 240) { // 240X320 ��Ļ
			return 20;
		} else if (screenWidth <= 320) { // 320X480 ��Ļ
			return 24;
		} else if (screenWidth <= 480) { // 480X800 �� 480X854 ��Ļ
			return 34;
		} else if (screenWidth <= 540) { // 540X960 ��Ļ
			return 36;
		} else if (screenWidth <= 800) { // 800X1280 ��Ļ
			return 40;
		} else { // ���� 800X1280
			return 40;
		}
	}

}
