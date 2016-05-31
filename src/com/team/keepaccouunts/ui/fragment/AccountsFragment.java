package com.team.keepaccouunts.ui.fragment;

import com.team.keepaccouunts.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 流水帐页面碎片
 * 
 * @author xiaoqiang
 *
 */
public class AccountsFragment extends Fragment {

	View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_accounts, container, false);

		return view;
	}
}
