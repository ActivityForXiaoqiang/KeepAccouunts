package com.team.keepaccouunts;

import com.team.keepaccouunts.db.DBHelper;
import com.team.keepaccouunts.ui.fragment.AccountsFragment;
import com.team.keepaccouunts.ui.fragment.BudgetFragment;
import com.team.keepaccouunts.ui.fragment.HomeFragment;
import com.team.keepaccouunts.ui.fragment.WasteBookFragment;
import com.team.keepaccouunts.utils.DialogForAccount;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 主界面
 * 
 * @author xiaoqiang
 *
 */
public class MainActivity extends BaseActivity {
	private FragmentManager manager; // 碎片管理器
	private FragmentTransaction ft; // 碎片事件处理器
	private Fragment mFragment; // 当前显示碎片

	private AccountsFragment accounts;
	private BudgetFragment budget;
	private HomeFragment home;
	private WasteBookFragment wbook;

	private LinearLayout t_home, t_budget, t_accounts, t_wbook; // 底部按钮

	private ImageView add;

	DialogForAccount dfa;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		settime();
		init();
	}

	/**
	 * 初始化
	 */
	void init() {

		findID();
		home = new HomeFragment();
		accounts = new AccountsFragment();
		budget = new BudgetFragment();
		wbook = new WasteBookFragment();

		manager = getSupportFragmentManager();
		ft = manager.beginTransaction();
		ft.add(R.id.main_fragment, home).commit();
		mFragment = home;

	}

	/**
	 * 控件初始化
	 */
	void findID() {
		t_accounts = (LinearLayout) findViewById(R.id.tab3);
		t_budget = (LinearLayout) findViewById(R.id.tab4);
		t_home = (LinearLayout) findViewById(R.id.tab1);
		t_wbook = (LinearLayout) findViewById(R.id.tab2);
		t_accounts.setOnClickListener(l);
		t_budget.setOnClickListener(l);
		t_home.setOnClickListener(l);
		t_wbook.setOnClickListener(l);

		dfa = new DialogForAccount(this);
		add = (ImageView) findViewById(R.id.main_top_add);
		add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// showAddAccount();
				dfa.showAddAccount();
			}
		});
	}

	/**
	 * 底部tab按钮的监听
	 */
	OnClickListener l = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.tab1:
				ShowView(mFragment, home);
				break;
			case R.id.tab2:
				ShowView(mFragment, wbook);
				break;
			case R.id.tab3:
				ShowView(mFragment, accounts);
				break;
			case R.id.tab4:
				ShowView(mFragment, budget);
				break;
			}
		}
	};

	/**
	 * 显示碎片
	 * 
	 * @param from
	 * @param to
	 */
	void ShowView(Fragment from, Fragment to) {
		if (mFragment == to) {
			return;
		}
		ft = manager.beginTransaction().setCustomAnimations(android.R.anim.slide_in_left,
				android.R.anim.slide_out_right);
		if (!to.isAdded()) {

			// Toast.makeText(this, "目标碎片加载" + to.isAdded(), 0).show();
			ft.hide(from).add(R.id.main_fragment, to).commit();

		} else {
			// Toast.makeText(this, "目标碎片已被加载" + to.isAdded(), 0).show();
			ft.hide(from).show(to).commit();
		}
		mFragment = to;
	}

	/**
	 * 获得当前界面碎片
	 * 
	 * @return
	 */
	Fragment CurrentFragment() {

		return mFragment;
	}

	AlertDialog dialog;

	public void showAddAccount() {
		dialog = new AlertDialog.Builder(this).create();
		dialog.show();
		Window window = dialog.getWindow();
		window.setContentView(R.layout.dialog_add_account);
		TextView add_account, add_group, cancal;
		add_account = (TextView) window.findViewById(R.id.add_account);
		add_group = (TextView) window.findViewById(R.id.add_account_group);
		cancal = (TextView) window.findViewById(R.id.cancal);
		add_account.setOnClickListener(AddListenr);
	}

	OnClickListener AddListenr = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.add_account:
				startActivity(new Intent(MainActivity.this, AccountAddActivity.class));
				break;

			default:
				break;
			}
		}
	};

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// if (resultCode == getActivity().RESULT_OK) {
		Log.i("xiaoqiang", "testing");
		if (requestCode == 1001) {
			String mode = data.getStringExtra("mode");
			int i = data.getIntExtra("add", 0);
			if (TextUtils.isEmpty(mode)) {
				return;
			}
			if (mode.equals(DBHelper.GET)) {
				home.setget(i);
				
			}
			if (mode.equals(DBHelper.PAY)) {
				home.setpay(i);
				
			}

		}
		// }
	}

	public interface change {
		public void setget(int i);

		public void setpay(int i);
	}

}
