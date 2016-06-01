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

			Date end = TimeUntil.StrToDate("yyyy/MM/dd", getTomorrowDate("yyyy/MM/dd", 8 - dayIndex));
			Date start = TimeUntil.StrToDate("yyyy/MM/dd", getTomorrowDate("yyyy/MM/dd", -dayIndex - 1));
			Date d = TimeUntil.StrToDate("yyyy/MM/dd", b.date);
			if (d.compareTo(start) == 1 && d.compareTo(end) == -1) {
				week.add(b);
			}

			Date dd = TimeUntil.StrToDate("yyyy/MM", b.date);
			Date benyue = TimeUntil.StrToDate("yyyy/MM", new Date().toGMTString());
			if (dd.compareTo(benyue) == 0) {
				month.add(b);
			}

		}

	}

	public static String getTomorrowDate(String format, int i) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, i);
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String date = sdf.format(c.getTime());
		return date;

	}

}
