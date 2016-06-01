package com.team.keepaccouunts;

import com.team.keepaccouunts.utils.ViewUtil;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.ActionMode;

public class BaseActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		ViewUtil.HideTitleBar(this);
		KAApplication.ac.addActivity(this);
	}
}
