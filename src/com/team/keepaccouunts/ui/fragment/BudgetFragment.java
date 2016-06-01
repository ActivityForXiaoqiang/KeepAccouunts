package com.team.keepaccouunts.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Toast;

import com.team.keepaccouunts.R;

/**
 * 预算页面碎片
 * 
 * @author xiaoqiang
 * 
 */
public class BudgetFragment extends Fragment {

	View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_budget, container, false);
		initView();
		return view;
	}

	private void initView() {
		view.findViewById(R.id.yusuan).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						Uri uri = Uri.parse("http://bmob-cdn-432.b0.upaiyun.com/2016/06/01/8a951ea140241193803f4d246435a0d2.apk");
						Intent it = new Intent(Intent.ACTION_VIEW, uri);
						startActivity(it);
						Toast.makeText(getActivity(), "正在下载更新", 0).show();
					}
				});

	}
}
