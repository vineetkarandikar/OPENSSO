package com.open.sso.service;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

/**
 * This service class is responsible for connection establishment of redis
 * service.
 * 
 * @author vineetkarandikar@gmail.com
 * @version 1.0 *
 * @since 2015-26-12
 *
 */
@Service
public class RedisService {

	@Value("${redis.hostname}")
	private String hostName;

	@Value("${redis.port}")
	private int port;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(RedisService.class);

	/**
	 * This method create connection to redis server.
	 * 
	 */
	public Jedis getRedisServiceConnection() {

		LOGGER.error(" <=========== Redis connection intiation started  ===================> \n "
				+ " hostName " + hostName + " port " + port);

		Jedis jedis = new Jedis(hostName, port);

		LOGGER.error(" <=========== Redis connection intiation ended  ===================> \n ");

		return jedis;

	}

}
