package com.team.keepaccouunts.db;

import com.team.keepaccouunts.base.Account;
import com.team.keepaccouunts.base.AccountGroup;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	public static final String Bill = "bill";
	public static final String Account = "account";
	public static final String Account_group = "account_group";

	public DBHelper(Context context) {
		super(context, "keep.db", null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(
				"create table account_group(_id integer primary key autoincrement,name varchar(200),describe varchar(200))");
		db.execSQL(
				"create table account(_id integer primary key autoincrement,name varchar(200),ag_id integer,money varchar(200),describe varchar(200))");
		db.execSQL(
				"create table bill(_id integer primary key autoincrement,mode varchar(200),money integer,type varchar(200),account_id varchar(200),describe varchar(200),date varchar(200))");

//		dataInit();
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

	}

	void dataInit() {
		AccountGroup g = new AccountGroup();
		g.name = "默认";
		g.describe = "默认组";

		DBUtil.getInstance().save(DBHelper.Account_group, g);

		Account account = new Account();
		account.describe = "默认用户";
		account.group = g;
		account.group_id = 0;
		account.money = "0";
		account.name = "默认帐户";

		DBUtil.getInstance().save(DBHelper.Account, account);

	}

}
