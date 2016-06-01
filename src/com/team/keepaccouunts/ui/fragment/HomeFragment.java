package com.team.keepaccouunts.ui.fragment;

import com.team.keepaccouunts.BillAddActivity;
import com.team.keepaccouunts.R;

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

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_home, container, false);
		init();
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
}
