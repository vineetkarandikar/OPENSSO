package com.open.sso.model;

import java.util.List;

/**
 * This pojo class is for user.
 * 
 * @author vineetkarandikar@gmail.com
 * @version 1.0
 * @since 2015-26-12
 *
 */

public class User {

	private String userName;

	private String password;

	private List<String> roles;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password
				+ ", roles=" + roles + "]";
	}

}
