package com.team.keepaccouunts.adapter;

import java.util.ArrayList;
import java.util.List;

import com.team.keepaccouunts.BillAddActivity;
import com.team.keepaccouunts.R;
import com.team.keepaccouunts.base.Bill;
import com.team.keepaccouunts.db.DBHelper;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * 账单listview适配器
 * 
 * @author xiaoqiang
 *
 */
public class BillAdapter extends BaseAdapter {
	Context con;
	List<Bill> data;
	List<Bill> newdata = new ArrayList<Bill>();

	public BillAdapter(Context con, List<Bill> data) {
		this.data = data;
		this.con = con;
		if (data != null && data.size() > 0) {
			deal();
			Log.i("xiaoqing", "sss");
		}
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return newdata == null ? 3 : newdata.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return newdata.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Viewholder h;
		if (convertView == null) {
			convertView = View.inflate(con, R.layout.listview_item_bill, null);
			h = new Viewholder();
			// h.account = (TextView)
			// convertView.findViewById(R.id.bill_account);
			h.date = (TextView) convertView.findViewById(R.id.bill_date);
			h.mode = (TextView) convertView.findViewById(R.id.bill_mode);
			h.money = (TextView) convertView.findViewById(R.id.bill_money);
			h.type = (TextView) convertView.findViewById(R.id.bill_type);
			convertView.setTag(h);
		} else {
			h = (Viewholder) convertView.getTag();
		}

		if (newdata != null && newdata.size() > 0) {
			Bill b = newdata.get(position);
			h.date.setText(b.date);
			h.mode.setText("");
			h.money.setText(b.money);
			h.type.setText(b.type);
			// dddd
		}

		return convertView;
	}

	void deal() {

		List<Bill> pp = new ArrayList<Bill>();
		for (int j = 0; j < BillAddActivity.type.length; j++) {
			if (j == BillAddActivity.type.length) {
				return;
			}
			int k = 0;
			for (int i = 0; i < data.size(); i++) {
				// Log.i("xiaoqiang", "fori");
				Bill b = data.get(i);
				if (b.type.equals(BillAddActivity.type[j])) {

					if (b.mode.equals(DBHelper.GET)) {
						k = k + new Integer(b.money);
					} else {
						k = k - new Integer(b.money);
					}
					pp.add(b);
				}
			}
			if (pp.size() > 0) {
				Bill l = pp.get(pp.size() - 1);
				l.money = k + "";
				Log.i("xiaoqiang", k+"--======");
				newdata.add(l);
			}
			
			pp.clear();

		}
		Log.i("xiaoqiang", newdata.size() + "........");
	}

	class Viewholder {
		TextView date, type, mode, money;
	}

}
