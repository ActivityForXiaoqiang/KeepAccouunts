package com.team.keepaccouunts.utils;

import java.util.Stack;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

public class ActivityCollector {

	private static Stack<Activity> stackActivities;
	private static ActivityCollector instance;

	private ActivityCollector() {
	}

	/**
	 * 单列
	 * 
	 * @return
	 */
	public static ActivityCollector getActivityCollector() {
		if (instance == null) {
			instance = new ActivityCollector();
		}
		return instance;
	}

	/**
	 * 加入栈
	 * 
	 * @param activity
	 */

	public void addActivity(Activity activity) {
		if (stackActivities == null) {
			stackActivities = new Stack<Activity>();
		}
		stackActivities.add(activity);
	}

	/**
	 * 关闭当前Activity
	 * 
	 * @return
	 */

	public void finishCurrentActivity() {
		Activity activity = stackActivities.lastElement();
		finishAssignActivity(activity);
	}

	public void finishAssignActivity(Activity activity) {
		if (activity != null) {
			stackActivities.remove(activity);
			activity.finish();
			activity = null;

		}
	}

	public void finishAllActivity() {
		for (int i = 0, size = stackActivities.size(); i < size; i++) {
			if (null != stackActivities.get(i)) {
				stackActivities.get(i).finish();
			}
		}
		stackActivities.clear();
	}

	public Activity getCurrentActivity() {
		Activity activity = stackActivities.lastElement();
		return activity;
	}

	public void finishActivity(Class<?> cls) {
		for (Activity activity : stackActivities) {
			if (activity.getClass().equals(cls)) {
				finishAssignActivity(activity);
			}
		}
	}

	/**
	 * 关闭所有Activity
	 * 
	 * @param context
	 */
	public void AppExit(Context context) {
		try {
			finishAllActivity();
			ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
			activityManager.restartPackage(context.getPackageName());
			System.exit(0);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
