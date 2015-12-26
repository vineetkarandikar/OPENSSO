package com.open.sso.service;

import java.util.Set;
import java.util.concurrent.Future;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import com.open.sso.common.SSOConstants;
import com.open.sso.model.User;
import com.open.sso.model.Role;

/**
 * This service class is responsible for user service operation.
 * 
 * @author vineetkarandikar@gmail.com
 * @version 1.0
 * @since 2015-26-12
 *
 */

@Service
public class UserService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(UserService.class);

	@Autowired
	private RedisService redisService;

	/**
	 * This method save username as a key and passsword as a value in redis.
	 */
	@Async
	public Future<User> saveUser(User user) {

		LOGGER.info(" <=========== Save User Service : Started ===================> \n");

		redisService.getRedisServiceConnection().set(user.getUserName(),
				user.getPassword());
		for (String role : user.getRoles()) {
			redisService.getRedisServiceConnection().sadd(
					user.getUserName() + SSOConstants.ROLES, role);
		}

		LOGGER.info(" <=========== Save User Service : Success with username"
				+ user.getUserName() + " and password is " + user.getPassword()
				+ " ===================> \n");

		return new AsyncResult<User>(user);

	}

	/**
	 * This method save role name as a key for SET and it's values as URLS in
	 * redis.
	 */
	@Async
	public Future<Role> configureRoleBasedMapping(Role userRole) {

		LOGGER.info(" <=========== Configure Role Based Mapping service : Started  ===================> \n");

		for (String roleMapping : userRole.getMappingUrls()) {
			redisService.getRedisServiceConnection().sadd(
					userRole.getRoleName(), roleMapping);
		}

		LOGGER.info(" <=========== Configure Role Based Mapping service : ended  ===================> \n");

		return new AsyncResult<Role>(userRole);

	}

	/**
	 * This method creates a set with user specific role as a key and it's
	 * values as URLS allowed. redis.
	 */
	@Async
	public Future<User> configureRoleBasedMappingForUser(User user) {

		LOGGER.info(" <=========== Configure RoleBasedMapping For User service : Started  ===================> \n");

		for (String role : user.getRoles()) {
			Set<String> rolemappings = redisService.getRedisServiceConnection()
					.smembers(role);
			for (String url : rolemappings) {
				redisService.getRedisServiceConnection().sadd(
						user.getUserName() + SSOConstants.ROLE_MAPPING, url);
			}

		}

		LOGGER.info(" <=========== Configure RoleBasedMapping For User service : ended  ===================> \n");

		return new AsyncResult<User>(user);

	}

}
