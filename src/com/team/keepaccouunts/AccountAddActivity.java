package com.team.keepaccouunts;

import java.util.ArrayList;
import java.util.List;

import com.team.keepaccouunts.base.Account;
import com.team.keepaccouunts.base.AccountGroup;
import com.team.keepaccouunts.db.DBHelper;
import com.team.keepaccouunts.db.DBUtil;
import com.team.keepaccouunts.utils.ActivityCollector;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 添加帐户
 * 
 * @author xiaoqiang
 *
 */
public class AccountAddActivity extends BaseActivity {

	int index;
	DBUtil db = DBUtil.getInstance();
	private ImageView add;
	private EditText account_name, account_money, account_describe;
	private TextView account_group, submit;
	private List<Object> groups;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_add_account);
		settime();
		findId();
		dataInit();

	}

	void findId() {
		account_name = (EditText) findViewById(R.id.account_name_add);
		account_money = (EditText) findViewById(R.id.account_money_money);
		account_describe = (EditText) findViewById(R.id.account_miaoshu_add);

		account_group = (TextView) findViewById(R.id.account_group_add);
		account_group.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showGroupsDialog();
			}
		});
		submit = (TextView) findViewById(R.id.account_add_submit);
		submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				save();
			}
		});
		add = (ImageView) findViewById(R.id.main_top_add);
		add.setVisibility(View.GONE);
	}

	void dataInit() {
		groups = db.query(DBHelper.Account_group, null, null);
		// Log.i("xiaoqiang", groups.size()+"");
	}

	void showGroupsDialog() {
		final AlertDialog dialog = new AlertDialog.Builder(this).create();
		dialog.show();
		Window window = dialog.getWindow();
		window.setContentView(R.layout.dialog_type);
		ListView listView = (ListView) window.findViewById(R.id.dialog_listview_type);
		final List<String> strData = new ArrayList<String>();
		for (int i = 0; i < groups.size(); i++) {
			AccountGroup g = (AccountGroup) groups.get(i);
			strData.add(g.name);
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strData);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				account_group.setText(strData.get(position));
				index = position + 1;
				dialog.dismiss();
			}
		});
	}

	void save() {
		Account a = new Account();
		a.name = account_name.getText().toString();
		a.describe = account_describe.getText().toString();
		a.money = account_money.getText().toString();
		a.group_id = index;

		db.save(DBHelper.Account, a);
		Toast.makeText(this, "保存成功", 0).show();
		ActivityCollector.getActivityCollector().finishCurrentActivity();
	}

}
