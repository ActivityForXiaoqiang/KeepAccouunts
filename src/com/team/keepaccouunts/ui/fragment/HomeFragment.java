package com.team.keepaccouunts.ui.fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.team.keepaccouunts.BillAddActivity;
import com.team.keepaccouunts.R;
import com.team.keepaccouunts.base.Bill;
import com.team.keepaccouunts.db.DBHelper;
import com.team.keepaccouunts.db.DBUtil;
import com.team.keepaccouunts.utils.TimeUntil;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 主页碎片
 * 
 * @author xiaoqiang
 *
 */
public class HomeFragment extends Fragment {
	View view;
	TextView add_bill;
	List<Object> data;
	List<Bill> today;
	List<Bill> week;
	List<Bill> month;

	TextView day, dweek, dmouth;
	TextView tpay, tget, wpay, wget, mpay, mget;
	int today_Intpay = 0;
	int today_Intget = 0;

	int dweek_Intpay = 0;
	int dweek_Intget = 0;

	int dmouth_Intpay = 0;
	int dmouth_Intget = 0;
	TextView t_get, t_pay, t_yusuan;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_home, container, false);
		init();
		datainit();
		return view;
	}

	void init() {
		add_bill = (TextView) view.findViewById(R.id.main_add_bill);
		add_bill.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				getActivity().startActivity(new Intent(getActivity(), BillAddActivity.class));
			}
		});
		day = (TextView) view.findViewById(R.id.today_date);
		dweek = (TextView) view.findViewById(R.id.week_date);
		dmouth = (TextView) view.findViewById(R.id.mouth_date);
		day.setText(TimeUntil.getLocalDate("yyyy/MM/dd"));
		dweek.setText(TimeUntil.getLocalDate("yyyy/MM/dd"));
		dmouth.setText(TimeUntil.getLocalDate("yyyy/MM/dd"));

		tget = (TextView) view.findViewById(R.id.today_get);
		tpay = (TextView) view.findViewById(R.id.today_pay);

		mget = (TextView) view.findViewById(R.id.mouth_get);
		mpay = (TextView) view.findViewById(R.id.mouth_pay);

		wget = (TextView) view.findViewById(R.id.week_get);
		wpay = (TextView) view.findViewById(R.id.week_pay);

		t_get = (TextView) view.findViewById(R.id.z_shouru);
		t_pay = (TextView) view.findViewById(R.id.t_zhichu);

	}

	void datainit() {
		data = DBUtil.getInstance().query(DBHelper.Bill, null, null);
		today = new ArrayList<Bill>();
		week = new ArrayList<Bill>();
		month = new ArrayList<Bill>();

		for (int i = 0; i < data.size(); i++) {
			Bill b = (Bill) data.get(i);
			if (b.date.equals(TimeUntil.getLocalDate("yyyy/MM/dd"))) {
				today.add(b);
			}
			Calendar c = Calendar.getInstance(Locale.CHINA);
			c.setTime(new Date());
			int dayIndex = c.get(Calendar.DAY_OF_WEEK);

			Date end = TimeUntil.StrToDate("yyyy/MM/dd", getTomorrowDate("yyyy/MM/dd", 7 - dayIndex));
			Date start = TimeUntil.StrToDate("yyyy/MM/dd", getTomorrowDate("yyyy/MM/dd", -dayIndex));
			Date d = TimeUntil.StrToDate("yyyy/MM/dd", b.date);
			if (d.compareTo(start) == 1 && d.compareTo(end) == -1) {
				week.add(b);
			}

			Date dd = TimeUntil.StrToDate("yyyy/MM/dd", b.date);
			Date benyue = TimeUntil.StrToDate("yyyy/MM/dd", new Date().toGMTString());
			if (dd.compareTo(benyue) < 0) {
				month.add(b);
			}

		}

		for (int i = 0; i < today.size(); i++) {
			Bill b = today.get(i);
			if (b.mode.equals(DBHelper.GET)) {
				today_Intget = today_Intget + new Integer(b.money);
			}
			if (b.mode.equals(DBHelper.PAY)) {
				today_Intpay = today_Intpay + new Integer(b.money);
			}
		}
		Log.i("xiaoqiang", today_Intget + "" + today_Intpay);
		tget.setText(today_Intget + "");
		tpay.setText(today_Intpay + "");

		for (int i = 0; i < week.size(); i++) {
			Bill b = week.get(i);
			if (b.mode.equals(DBHelper.GET)) {
				dweek_Intget = dweek_Intget + new Integer(b.money);
			}
			if (b.mode.equals(DBHelper.PAY)) {
				dweek_Intpay = dweek_Intpay + new Integer(b.money);
			}
		}
		wget.setText(dweek_Intget + "");
		wpay.setText(dweek_Intpay + "");

		for (int i = 0; i < month.size(); i++) {
			Bill b = month.get(i);
			if (b.mode.equals(DBHelper.GET)) {
				dmouth_Intget = dmouth_Intget + new Integer(b.money);
			}
			if (b.mode.equals(DBHelper.PAY)) {
				dmouth_Intpay = dmouth_Intpay + new Integer(b.money);
			}
		}
		mget.setText(dmouth_Intget + "");
		mpay.setText(dmouth_Intpay + "");

		t_get.setText(dmouth_Intget + "");
		t_pay.setText(dmouth_Intpay + "");
	}

	public static String getTomorrowDate(String format, int i) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, i);
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String date = sdf.format(c.getTime());
		return date;

	}

}
