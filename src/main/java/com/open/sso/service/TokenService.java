package com.open.sso.service;

import java.util.UUID;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import com.open.sso.common.SSOConstants;
import com.open.sso.common.SSOException;
import com.open.sso.model.SSORespone;

/**
 * This service class is responsible for generation of token.
 * 
 * @author vineetkarandikar@gmail.com
 * @version 1.0
 * @since 2015-26-12
 *
 */

@Service
public class TokenService {

	@Autowired
	private RedisService redisService;

	@Autowired
	private SSORespone ssoResponse;

	@Value("${token.default.time}")
	private int defaultTime;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(TokenService.class);

	/**
	 * This method executes business logic for generation of token.
	 */

	@Async
	public Future<SSORespone> generateToken(String username, String password,
			int expirytime) throws SSOException {

		LOGGER.info(" <=========== Token Service : Started generating token ===================> \n");

		if (password.equals(redisService.getRedisServiceConnection().get(
				username)))

		{
			String uuid = UUID.randomUUID().toString();

			if (expirytime == 0)

			{

				expirytime = defaultTime;

			}

			redisService.getRedisServiceConnection().setex(
					username + SSOConstants.TOKEN, expirytime, uuid);

			ssoResponse.setStatus(HttpStatus.OK.toString());

			ssoResponse.setToken(uuid);

			LOGGER.info(" <=========== Token Service : Token Number is  ===================> \n "
					+ uuid);

			return new AsyncResult<SSORespone>(ssoResponse);

		}

		else {

			LOGGER.error(" <=========== Invalid UserName Password combination  ===================> \n "
					+ " username " + username + " password " + password);

			throw new SSOException(HttpStatus.UNAUTHORIZED.toString());

		}

	}

}
