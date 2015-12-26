package com.open.sso.controller;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.open.sso.model.SSORespone;
import com.open.sso.service.AuthenticationAndAuthorizationService;
import com.open.sso.service.TokenService;

/**
 * This controller class is responsible for authentication & authorization
 * activity.
 * 
 * @author vineetkarandikar@gmail.com
 * @version 1.0
 * @since 2015-26-12
 *
 */

@RequestMapping("/token/v1")
public class Authentication {

	@Autowired
	private AuthenticationAndAuthorizationService authenticationAndAuthorizationService;

	@Autowired
	private TokenService tokenService;

	/**
	 * This Method handles generation of token for supplied username and
	 * password.
	 */
	@RequestMapping(value = "/generatetoken", method = RequestMethod.POST)
	@ResponseBody
	public SSORespone generateToken(
			@RequestHeader(value = "username") String username,
			@RequestHeader(value = "password") String password,
			@RequestHeader(value = "expirytime") int expirytime)
			throws InterruptedException, ExecutionException {

		Future<SSORespone> ssoRespone = tokenService.generateToken(username,
				password, expirytime);

		return ssoRespone.get();

	}

	/**
	 * This Method handles authentication of token and authorization of
	 * requested url
	 */
	@RequestMapping(value = "/authenticate", method = RequestMethod.GET)
	@ResponseBody
	public SSORespone authenicateAndAuthorize(
			@RequestHeader(value = "username") String username,
			@RequestHeader(value = "token") String token,
			@RequestHeader(value = "requesteduri") String requesteduri) {
		return authenticationAndAuthorizationService
				.authenticateAndAuthorization(username, token, requesteduri);
	}

}