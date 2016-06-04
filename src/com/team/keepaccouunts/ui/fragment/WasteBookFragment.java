package com.team.keepaccouunts.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import com.team.keepaccouunts.R;
import com.team.keepaccouunts.BaseListener;
import com.team.keepaccouunts.adapter.BillAdapter;
import com.team.keepaccouunts.base.Bill;
import com.team.keepaccouunts.db.DBHelper;
import com.team.keepaccouunts.db.DBUtil;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * 流水帐页面碎片
 * 
 * @author xiaoqiang
 *
 */
public class WasteBookFragment extends Fragment implements BaseListener {
	View view;
	ListView listview;
	BillAdapter adapter;
	List<Object> data;
	List<Bill> d;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_wastebook, container, false);
		datainit();
		init();
		return view;
	}

	void init() {
		listview = (ListView) view.findViewById(R.id.list_wasteBook);
		adapter = new BillAdapter(getActivity(), d);
		listview.setAdapter(adapter);
	}

	void datainit() {
		data = DBUtil.getInstance().query(DBHelper.Bill, null, null);

		d = new ArrayList<Bill>();
		for (int i = 0; i < data.size(); i++) {
			Bill b = (Bill) data.get(i);
			d.add(b);
		}
	}

	@Override
	public void dataChange() {
//		data.clear();
//		d.clear();
		data = DBUtil.getInstance().query(DBHelper.Bill, null, null);
		d = new ArrayList<Bill>();
		for (int i = 0; i < data.size(); i++) {
			Bill b = (Bill) data.get(i);
			d.add(b);
		}
		BillAdapter adapter = new BillAdapter(getActivity(), d);
		listview.setAdapter(adapter);
	}

}
