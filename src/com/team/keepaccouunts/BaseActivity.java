package com.team.keepaccouunts;

import com.team.keepaccouunts.utils.TimeUntil;
import com.team.keepaccouunts.utils.ViewUtil;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.ActionMode;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class BaseActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		ViewUtil.HideTitleBar(this);
		KAApplication.ac.addActivity(this);
	}

	void settime() {
		TextView toptitle=(TextView) findViewById(R.id.main_top_title);
		toptitle.setText(TimeUntil.getLocalDate("yyyy/MM/dd"));
	}

	
}
