package com.team.keepaccouunts.utils;

import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;
/**
 * 视图工具类
 * @author xiaoqiang
 *
 */
public class ViewUtil {
	/**
	 * Activity 顶部标题栏隐藏
	 * 
	 * @param activity
	 */
	public static void HideTitleBar(Activity activity) {
		activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
	}

	/**
	 * Activity 顶部状态栏隐藏
	 * 
	 * @param activity
	 */

	public static void HideStatusBar(Activity activity) {
		activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}

}
