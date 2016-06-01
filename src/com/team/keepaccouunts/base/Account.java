package com.team.keepaccouunts.base;

/**
 * 帐户对象
 * 
 * @author xiaoqiang
 * 
 */
public class Account {
	public int id;
	public String name;

	public int group_id;
	public String money;
	public String describe;
	public AccountGroup group;

	public Account() {
		super();
	}

	public Account(int id, String name, int group_id, String money, String describe) {
		super();
		this.id = id;
		this.name = name;
		this.group_id = group_id;
		this.money = money;
		this.describe = describe;
	}

}
