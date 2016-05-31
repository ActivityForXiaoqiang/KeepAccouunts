package com.team.keepaccouunts.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.util.Log;

/**
 * 时间获取，格式转换
 * 
 * @author xiaoqiang
 *
 */
public class TimeUntil {

	private static String[] DayOfWeekValues = { "周日", "周一", "周二", "周三", "周四", "周五", "周六" };

	/**
	 * 获取当前本地日期，返回string
	 * 
	 * @param format
	 *            日期格式 例如 yyyy年MM月dd日
	 * @return
	 */
	public static String getLocalDate(String format) {

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		java.util.Date date = new java.util.Date();
		String str = sdf.format(date);

		return str;

	}

	/**
	 * 获取当前本地时间，返回string
	 * 
	 * @param format
	 *            时间格式 例如 HH:mm
	 * @return
	 */
	public static String getLocalTime(String format) {
		DateFormat df = new SimpleDateFormat(format);
		return df.format(new Date());
	}

	/**
	 * 字符串转换成日期类
	 * 
	 * @param format
	 *            例如 "yyyy年MM月dd日 HH:mm" or "yyyy年MM月dd日"
	 * @param dateStr
	 *            字符串格式时间或 日期
	 * @return
	 */
	public static Date StrToDate(String format, String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return date = new Date();
		}

		return date;

	}

	/**
	 * 日期时间转换成字符串
	 * 
	 * @param date
	 * @param format
	 *            "yyyy年MM月dd日" or "yyyy年MM月dd日 HH:mm"
	 * @return
	 */
	public static String DateToStr(Date date, String format) {

		SimpleDateFormat sdf = new SimpleDateFormat(format);

		return sdf.format(date);

	}

	/**
	 * 获得明天日期
	 * 
	 * @param format
	 *            例如 "yyyy年MM月dd日" or "yyyy年MM月dd日 HH:mm"
	 * @return
	 */
	public static String getTomorrowDate(String format) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, 1);
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String date = sdf.format(c.getTime());
		return date;

	}

	/**
	 * 获取时间在一周当中的哪一天
	 * 
	 * @param date
	 * @return
	 */
	public static String getDayOfWeek(Date date) {

		Calendar c = Calendar.getInstance(Locale.CHINA);
		c.setTime(date);
		int dayIndex = c.get(Calendar.DAY_OF_WEEK);
		String day = null;
		day = DayOfWeekValues[dayIndex - 1];

		return day;

	}

	/**
	 * 获取时间在一月之中的哪一天
	 * 
	 * @param date
	 * @return
	 */

	public static int getDayOfMouth(Date date) {

		Calendar c = Calendar.getInstance(Locale.CHINA);
		c.setTime(date);
		int dayIndex = c.get(Calendar.DAY_OF_MONTH);

		return dayIndex;

	}

}
