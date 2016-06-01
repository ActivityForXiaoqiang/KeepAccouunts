package com.team.keepaccouunts.utils;

import com.team.keepaccouunts.AccountAddActivity;
import com.team.keepaccouunts.GroupAddActivity;
import com.team.keepaccouunts.MainActivity;
import com.team.keepaccouunts.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class DialogForAccount {
	Context con;
	Activity activity;

	public DialogForAccount(Context con) {
		this.con = con;
		activity = (Activity) con;
	}

	AlertDialog dialog;

	public void showAddAccount() {
		dialog = new AlertDialog.Builder(con).create();
		dialog.show();
		Window window = dialog.getWindow();
		window.setContentView(R.layout.dialog_add_account);
		TextView add_account, add_group, cancal;
		add_account = (TextView) window.findViewById(R.id.add_account);
		add_group = (TextView) window.findViewById(R.id.add_account_group);
		cancal = (TextView) window.findViewById(R.id.cancal);
		add_account.setOnClickListener(AddListenr);
		add_group.setOnClickListener(AddListenr);
		cancal.setOnClickListener(AddListenr);
	}

	OnClickListener AddListenr = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.add_account:

				activity.startActivity(new Intent(activity, AccountAddActivity.class));
				break;
			case R.id.add_account_group:
				activity.startActivity(new Intent(activity, GroupAddActivity.class));
				break;
			default:
				dialog.dismiss();
				break;
			}
			dialog.dismiss();
		}
	};
}
