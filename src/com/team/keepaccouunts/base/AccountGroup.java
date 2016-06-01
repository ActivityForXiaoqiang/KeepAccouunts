package com.team.keepaccouunts.base;

/**
 * 帐户组对象
 * 
 * @author xiaoqiang
 *
 */
public class AccountGroup {
	public int id;
	public String name;
	public String describe;

	public AccountGroup() {
		super();
	}

	public AccountGroup(int id, String name, String describe) {
		super();
		this.id = id;
		this.name = name;
		this.describe = describe;
	}
}
