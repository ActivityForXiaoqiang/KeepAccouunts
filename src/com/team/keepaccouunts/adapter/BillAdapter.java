package com.team.keepaccouunts.adapter;

import java.util.List;

import com.team.keepaccouunts.R;
import com.team.keepaccouunts.base.Bill;

import android.content.Context;
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

	public BillAdapter(Context con, List<Bill> data) {
		this.data = data;
		this.con = con;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data == null ? 3 : data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
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
			h.account = (TextView) convertView.findViewById(R.id.bill_account);
			h.date = (TextView) convertView.findViewById(R.id.bill_date);
			h.mode = (TextView) convertView.findViewById(R.id.bill_mode);
			h.money = (TextView) convertView.findViewById(R.id.bill_money);
			h.type = (TextView) convertView.findViewById(R.id.bill_type);
			convertView.setTag(h);
		}else{
			h = (Viewholder) convertView.getTag();
		}

		return convertView;
	}

	class Viewholder {
		TextView account, date, type, mode, money;
	}

}
