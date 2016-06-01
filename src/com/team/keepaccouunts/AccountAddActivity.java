package com.team.keepaccouunts;

import java.util.List;

import com.team.keepaccouunts.base.AccountGroup;
import com.team.keepaccouunts.db.DBHelper;
import com.team.keepaccouunts.db.DBUtil;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

/**
 * 添加帐户
 * 
 * @author xiaoqiang
 *
 */
public class AccountAddActivity extends BaseActivity {
	DBUtil db=DBUtil.getInstance();
	
	private EditText account_name, account_money, account_describe;
	private TextView account_group, submit;
	private List<Object> groups;
	
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_add_account);
	}

	void findId() {
		account_name = (EditText) findViewById(R.id.account_name_add);
		account_money = (EditText) findViewById(R.id.account_money_money);
		account_describe = (EditText) findViewById(R.id.account_group_add);

		account_group = (TextView) findViewById(R.id.account_group_add);
		submit = (TextView) findViewById(R.id.account_add_submit);
	}

	void dataInit() {
		groups=db.query(DBHelper.Account_group, null, null);
	}

}
