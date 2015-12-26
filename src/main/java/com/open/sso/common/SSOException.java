package com.open.sso.common;

/**
 * This is SSO specific customized runtime exception class
 * 
 * @author vineetkarandikar@gmail.com
 * @version 1.0
 * @since 2015-26-12
 *
 */

public class SSOException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SSOException(String message) {
		super(message);
	}

}
