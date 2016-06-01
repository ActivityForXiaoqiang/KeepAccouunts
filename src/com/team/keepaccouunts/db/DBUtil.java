package com.team.keepaccouunts.db;

import java.util.ArrayList;

import com.team.keepaccouunts.base.Account;
import com.team.keepaccouunts.base.AccountGroup;
import com.team.keepaccouunts.base.Bill;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBUtil {
	private static SQLiteDatabase db;
	private static DBUtil dbutil;

	public static synchronized DBUtil getInstance() {
		if (dbutil == null) {
			dbutil = new DBUtil();
		}
		return dbutil;
	}

	public void initSpManager(Context context) {
		DBHelper dbHelper = new DBHelper(context);
		db = dbHelper.getWritableDatabase();
	}

	/*
	 * 插入数据到表account
	 */
	public void save(String table, Account a) {
		ContentValues values = new ContentValues();
		values.put("name", a.name);
		values.put("ag_id", a.group_id);
		values.put("money", a.money);
		values.put("describe", a.describe);
		db.insert(table, null, values);
	}

	/*
	 * 插入数据到表account_group
	 */
	public void save(String table, AccountGroup ag) {
		ContentValues values = new ContentValues();
		values.put("name", ag.name);
		values.put("describe", ag.describe);
		db.insert(table, null, values);
	}

	/*
	 * 插入数据到表bill
	 */
	public void save(String table, Bill b) {
		ContentValues values = new ContentValues();
		values.put("mode", b.mode);
		values.put("money", b.money);
		values.put("type", b.type);
		values.put("account_id", b.account_id);
		values.put("date", b.date);
		values.put("describe", b.describe);
		db.insert(table, null, values);
	}

	public void delete(String table, String[] whereClause, String[] whereArgs) {
		db.delete(table, null, whereArgs);
	}

	public ArrayList<Object> query(String table, String selection, String[] selectionArgs) {
		Cursor c = db.query(table, null, selection, selectionArgs, null, null, null);
		ArrayList<Object> arrayList = new ArrayList<Object>();
		/*
		 * 查询账户
		 */
		if (table.equals("account")) {
			while (c.moveToNext()) {
				Account account = new Account();
				account.id = c.getInt(0);
				account.name = c.getString(1);
				account.group_id = c.getInt(2);
				account.money = c.getString(3);
				account.describe = c.getString(4);

				arrayList.add(account);
			}

		} else if (table.equals("account_group")) {
			/*
			 * 查询账户组
			 */
			while (c.moveToNext()) {
				AccountGroup ag = new AccountGroup();
				ag.id = c.getInt(0);
				ag.name = c.getString(1);
				ag.describe = c.getString(2);
				arrayList.add(ag);
			}
		} else if (table.equals("bill")) {
			/*
			 * 查询账单
			 */
			
			while (c.moveToNext()) {
				Bill b = new Bill();
				b.id = c.getInt(0);

				b.mode = c.getString(1);
				b.money = c.getInt(2) + "";
				b.type = c.getString(3);
				b.account_id = c.getInt(4);
				b.describe = c.getString(5);
				b.date = c.getString(6);
				arrayList.add(b);
			}
			/*
			 * 查询账单对应的账户
			 */
		}

		return arrayList;
	}

	/*
	 * 修改表account数据
	 */
	public void update(String table, Account a, String whereClause, String[] whereArgs) {
		ContentValues values = new ContentValues();
		values.put("name", a.name);
		values.put("ag_id", a.group_id);
		values.put("money", a.money);
		values.put("describe", a.describe);
		db.update(table, values, whereClause, whereArgs);
	}

	/*
	 * 修改表account_group数据
	 */
	public void update(String table, AccountGroup ag, String whereClause, String[] whereArgs) {
		ContentValues values = new ContentValues();
		values.put("name", ag.name);
		values.put("describe", ag.describe);
		db.update(table, values, whereClause, whereArgs);
	}

	/*
	 * 修改表bill数据
	 */
	public void update(String table, Bill b, String whereClause, String[] whereArgs) {
		ContentValues values = new ContentValues();
		values.put("mode", b.mode);
		values.put("money", b.money);
		values.put("type", b.type);
		values.put("account_id", b.account_id);
		values.put("date", b.date);
		values.put("describe", b.describe);
		db.update(table, values, whereClause, whereArgs);
	}
}
