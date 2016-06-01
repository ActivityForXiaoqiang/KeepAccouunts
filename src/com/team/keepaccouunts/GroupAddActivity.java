package com.team.keepaccouunts;

import com.team.keepaccouunts.base.AccountGroup;
import com.team.keepaccouunts.db.DBHelper;
import com.team.keepaccouunts.db.DBUtil;
import com.team.keepaccouunts.utils.ActivityCollector;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GroupAddActivity extends BaseActivity {

	EditText name, desc;
	TextView submit;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_add_group);
settime();
		findID();
	}

	void findID() {
		name = (EditText) findViewById(R.id.group_add);
		desc = (EditText) findViewById(R.id.group_miaoshu);
		submit = (TextView) findViewById(R.id.group_submit);
		submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				save();
			}
		});
	}

	void save() {
		AccountGroup g = new AccountGroup();
		g.name = name.getText().toString();
		g.describe = desc.getText().toString();

		DBUtil.getInstance().save(DBHelper.Account_group, g);
		Toast.makeText(this, "保存成功", 0).show();
		ActivityCollector.getActivityCollector().finishCurrentActivity();
	}

}
