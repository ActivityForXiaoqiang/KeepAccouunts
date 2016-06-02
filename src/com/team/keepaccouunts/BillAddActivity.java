package com.team.keepaccouunts;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.team.keepaccouunts.base.Account;
import com.team.keepaccouunts.base.Bill;
import com.team.keepaccouunts.db.DBHelper;
import com.team.keepaccouunts.db.DBUtil;
import com.team.keepaccouunts.utils.ActivityCollector;
import com.team.keepaccouunts.utils.DialogForAccount;
import com.team.keepaccouunts.utils.TimeUntil;

public class BillAddActivity extends BaseActivity {

	TextView dialog_type, account_chose, submit, mode;
	List<Account> account = new ArrayList<Account>();
	DBUtil db = DBUtil.getInstance();
	ImageView add, mode_chose;
	DialogForAccount dfa;

	EditText money, dec;
	int index;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_addbill);
		settime();
		init();
		// db.query(DBHelper.Account, null, null);
	}

	void init() {
		mode_chose = (ImageView) findViewById(R.id.mode_chose);
		mode_chose.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showModedialog();
			}
		});
		mode = (TextView) findViewById(R.id.mode);
		money = (EditText) findViewById(R.id.bill_add_money);
		dec = (EditText) findViewById(R.id.bill_miaoshu);
		dfa = new DialogForAccount(this);
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
				showGroupDialog();

			}
		});
		add = (ImageView) findViewById(R.id.main_top_add);
		add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// showAddAccount();
				dfa.showAddAccount();
			}
		});

		submit = (TextView) findViewById(R.id.bill_add);
		submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				save();
			}
		});

	}

	void showTypeDialog() {
		final AlertDialog dialog = new AlertDialog.Builder(this).create();
		dialog.show();
		Window window = dialog.getWindow();
		window.setContentView(R.layout.dialog_type);
		ListView listView = (ListView) window
				.findViewById(R.id.dialog_listview_type);
		final String[] type = { "吃饭", "购物", "娱乐", "约会", "赌博", "学习", "自定义" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, type);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (position != 6) {
					dialog_type.setText(type[position]);
				} else {
					showZidingyiDialog();
				}
				dialog.dismiss();
			}

		});

	}

	private void showZidingyiDialog() {
		final View textEntryView = LayoutInflater.from(this).inflate(
				R.layout.dialog_zidingyi, null);
		final EditText editText = (EditText) textEntryView
				.findViewById(R.id.dialog_zidingyi_edit);
		final AlertDialog dialog = new AlertDialog.Builder(this)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						String data=editText.getText().toString();
						if (TextUtils.isEmpty(data)) {
							Toast.makeText(BillAddActivity.this, "请输入名称", 0).show();
							return;
						}
						dialog_type.setText(data);
					}
				})
				.setView(textEntryView)
				.create();
		dialog.show();
	}

	List<Object> data;

	void showGroupDialog() {
		final AlertDialog dialog = new AlertDialog.Builder(this).create();
		dialog.show();
		Window window = dialog.getWindow();
		window.setContentView(R.layout.dialog_type);
		ListView listView = (ListView) window
				.findViewById(R.id.dialog_listview_type);
		final List<String> dataname = new ArrayList<String>();
		data = new ArrayList<Object>();
		data = db.query(DBHelper.Account, null, null);
		for (int i = 0; i < data.size(); i++) {
			Account a = (Account) data.get(i);
			dataname.add(a.name);
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, dataname);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				account_chose.setText(dataname.get(position));
				index = position + 1;
				dialog.dismiss();
			}
		});

	}

	AlertDialog dialog;

	void showModedialog() {
		dialog = new AlertDialog.Builder(this).create();
		dialog.show();
		Window window = dialog.getWindow();
		window.setContentView(R.layout.mode_view);
		TextView a, b;
		a = (TextView) window.findViewById(R.id.shouru);
		b = (TextView) window.findViewById(R.id.zhizhu);
		a.setOnClickListener(l);
		b.setOnClickListener(l);
	}

	String MODE = DBHelper.GET;
	OnClickListener l = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.shouru:
				mode.setText(DBHelper.GET);
				MODE = DBHelper.GET;
				break;

			default:
				mode.setText(DBHelper.PAY);
				MODE = DBHelper.PAY;
				break;
			}
			dialog.dismiss();
		}
	};

	void save() {
		Bill b = new Bill();
		b.account_id = index;
		b.date = TimeUntil.getLocalDate("yyyy/MM/dd");
		b.describe = dec.getText().toString();
		b.mode = MODE;
		b.money = money.getText().toString();
		b.type = dialog_type.getText().toString();
		db.save(DBHelper.Bill, b);
		Intent it = new Intent();
		it.putExtra("mode", MODE);
		it.putExtra("add", new Integer(b.money));
		setResult(RESULT_OK, it);

		Toast.makeText(this, "保存成功", 0).show();
		ActivityCollector.getActivityCollector().finishCurrentActivity();
	}

}
