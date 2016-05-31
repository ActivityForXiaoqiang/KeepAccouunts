package com.team.keepaccouunts;

import com.team.keepaccouunts.utils.ActivityCollector;

import android.app.Application;

public class KAApplication extends Application {

	public static ActivityCollector ac = ActivityCollector.getActivityCollector();

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();

	}
}
