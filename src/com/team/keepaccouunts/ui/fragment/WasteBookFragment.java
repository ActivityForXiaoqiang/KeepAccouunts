package com.team.keepaccouunts.ui.fragment;

import com.team.keepaccouunts.R;
import com.team.keepaccouunts.adapter.BillAdapter;

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
public class WasteBookFragment extends Fragment {
	View view;
	ListView listview;
	BillAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_wastebook, container, false);
		init();
		return view;
	}

	void init() {
		listview = (ListView) view.findViewById(R.id.list_wasteBook);
		adapter = new BillAdapter(getActivity(), null);
		listview.setAdapter(adapter);
	}
}
