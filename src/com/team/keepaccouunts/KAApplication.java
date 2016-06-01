package com.team.keepaccouunts;

import com.team.keepaccouunts.base.Account;
import com.team.keepaccouunts.base.AccountGroup;
import com.team.keepaccouunts.db.DBHelper;
import com.team.keepaccouunts.db.DBUtil;
import com.team.keepaccouunts.utils.ActivityCollector;

import android.app.Application;

public class KAApplication extends Application {

	public static ActivityCollector ac = ActivityCollector.getActivityCollector();

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		DBUtil.getInstance().initSpManager(this);
		
//		dataInit();
	}
	
	void dataInit() {
		AccountGroup g = new AccountGroup();
		g.name = "默认";
		g.describe = "默认组";

		DBUtil.getInstance().save(DBHelper.Account_group, g);

		Account account = new Account();
		account.describe = "默认用户";
		account.group_id = 1;
		account.money = "0";
		account.name = "默认帐户";

		DBUtil.getInstance().save(DBHelper.Account, account);

	}

}
