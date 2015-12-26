package com.open.sso.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.open.sso.common.SSOConstants;
import com.open.sso.common.SSOException;
import com.open.sso.model.SSORespone;

/**
 * This service class is responsible for authentication and authrization
 * process.
 * 
 * @author vineetkarandikar@gmail.com
 * @version 1.0 *
 * @since 2015-26-12
 *
 */
@Service
public class AuthenticationAndAuthorizationService {

	@Autowired
	private RedisService redisService;

	@Autowired
	private SSORespone ssoResponse;

	@Value("${token.default.time}")
	private int defaultTime;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(AuthenticationAndAuthorizationService.class);

	/**
	 * This method executes business logic for authentication and authorization
	 * process.
	 */
	public SSORespone authenticateAndAuthorization(String username,
			String token, String requesteduri) throws SSOException {

		LOGGER.info(" <=========== Token Validation Sevice started: ===================> \n");

		if (token.equals(redisService.getRedisServiceConnection().get(
				username + SSOConstants.TOKEN))) {

			redisService.getRedisServiceConnection().setex(
					username + SSOConstants.TOKEN,
					redisService.getRedisServiceConnection()
							.ttl(username + SSOConstants.TOKEN).intValue()
							+ defaultTime, token);

			if (redisService.getRedisServiceConnection().sismember(
					username + SSOConstants.ROLE_MAPPING, requesteduri)) {

				LOGGER.info(" <=========== Token Validation Success: ===================> \n");

				ssoResponse.setStatus(HttpStatus.OK.toString());

				ssoResponse.setToken(token);

				return ssoResponse;
			}

			else {

				LOGGER.info(" <=========== Token Authorization Failed : ===================> \n");

				throw new SSOException(HttpStatus.FORBIDDEN.toString());

			}

		}

		else

		{
			LOGGER.error(" <=========== Token Validation Failed: ===================> \n");

			throw new SSOException(HttpStatus.UNAUTHORIZED.toString());
		}
	}

}