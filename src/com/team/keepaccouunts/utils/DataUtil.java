package com.team.keepaccouunts.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 简单存储 实现本地存储
 * @author xiaoqiang
 */
public class DataUtil {

	/**
	 * 数据存储
	 * 
	 * @param con
	 * @param baseKey
	 * @param dataKey
	 * @param str
	 */
	public static void save(Context con, String baseKey, String dataKey, String str) {
		SharedPreferences sp = con.getSharedPreferences(baseKey, 0);
		SharedPreferences.Editor editor = sp.edit();
		editor.putString(dataKey, str);
		editor.commit();
	}

	/**
	 * 数据读取
	 * 
	 * @param con
	 * @param baseKey
	 * @param dataKey
	 * @return
	 */

	public static String read(Context con, String baseKey, String dataKey) {
		SharedPreferences sp = con.getSharedPreferences(baseKey, 0);

		return sp.getString(dataKey, null);
	}

	/**
	 * 数据删除
	 * 
	 * @param con
	 * @param baseKey
	 */

	public static void delete(Context con, String baseKey) {
		SharedPreferences sp = con.getSharedPreferences(baseKey, 0);
		SharedPreferences.Editor editor = sp.edit();
		editor.remove(baseKey);
		editor.clear();
		editor.commit();
	}

}
