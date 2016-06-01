package com.team.keepaccouunts;

import java.util.ArrayList;
import java.util.List;

import com.team.keepaccouunts.base.Account;
import com.team.keepaccouunts.db.DBHelper;
import com.team.keepaccouunts.db.DBUtil;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class BillAddActivity extends BaseActivity {

	TextView dialog_type, account_chose;
	List<Account> account = new ArrayList<Account>();
	DBUtil db = DBUtil.getInstance();

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_addbill);
		init();
		db.query(DBHelper.Account, null, null);
	}

	void init() {
		dialog_type = (TextView) findViewById(R.id.bill_type);
		dialog_type.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showTypeDialog();
			}
		});

		account_chose = (TextView) findViewById(R.id.bill_account_chose);
		account_chose.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});
	}

	void showTypeDialog() {
		AlertDialog dialog = new AlertDialog.Builder(this).create();
		dialog.show();
		Window window = dialog.getWindow();
		window.setContentView(R.layout.dialog_type);
		ListView listView = (ListView) window.findViewById(R.id.dialog_listview_type);
		String[] type = { "吃饭", "购物", "娱乐", "约会", "赌博", "学习" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, type);
		listView.setAdapter(adapter);

	}

	void showAccountDialog() {

	}

}
