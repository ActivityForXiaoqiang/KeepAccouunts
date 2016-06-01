package com.team.keepaccouunts.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.team.keepaccouunts.R;
import com.team.keepaccouunts.base.AccountGroup;
import com.team.keepaccouunts.db.DBHelper;
import com.team.keepaccouunts.db.DBUtil;

/**
 * 流水帐页面碎片
 * 
 * @author xiaoqiang
 * 
 */
public class AccountsFragment extends Fragment {

	View view;

	private ListView listView;
	
	List<Object> data;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_accounts, container, false);
         initVIew();
		return view;
	}

	private void initVIew() {
		data=DBUtil.getInstance().query(DBHelper.Account_group, null, null);
		List<String> dataString=new ArrayList<String>();
		for (int i = 0; i < data.size(); i++) {
			AccountGroup accountGroup=(AccountGroup) data.get(i);
			dataString.add(accountGroup.name);
		}
		
		listView=(ListView) view.findViewById(R.id.account_group_listview);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				getActivity(), android.R.layout.simple_list_item_1,dataString);
		listView.setAdapter(adapter);

	}
}
