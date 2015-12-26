package com.open.sso.model;

/**
 * This POJO class is generic response for open SSO access urls.
 * 
 * @author vineetkarandikar@gmail.com
 * @version 1.0
 * @since 2015-26-12
 *
 */

public class SSORespone {

	private String token;

	private String status;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "SSORespone [token=" + token + ", status=" + status + "]";
	}

}