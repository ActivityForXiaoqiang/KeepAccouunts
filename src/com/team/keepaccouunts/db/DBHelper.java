package com.team.keepaccouunts.db;

import com.team.keepaccouunts.base.Account;
import com.team.keepaccouunts.base.AccountGroup;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	public static final String Bill = "bill";
	public static final String Account = "account";
	public static final String Account_group = "account_group";
	public static final String GET = "收入";
	public static final String PAY = "支出";
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
				"create table bill(_id integer primary key autoincrement,mode varchar(200),money integer,type varchar(200),account_id integer,describe varchar(200),date varchar(200))");

		ContentValues values = new ContentValues();
		values.put("name", "默认");
		values.put("ag_id", 1);
		values.put("money", "0");
		values.put("describe", "默认");
		db.insert("account", null, values);
		values = new ContentValues();
		values.put("name", "默认组");
		values.put("describe", "默认");
		db.insert("account_group", null, values);

		// dataInit();
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

	}


}
