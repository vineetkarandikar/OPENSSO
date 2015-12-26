package com.open.sso.common;

import java.util.concurrent.ExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import com.open.sso.model.SSORespone;

/**
 * This class is common exception handler class for whole application
 * 
 * @author vineetkarandikar@gmail.com
 * @version 1.0
 * @since 2015-26-12
 *
 */

@ControllerAdvice
class SSOControllerAdvice {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(SSOControllerAdvice.class);

	@Autowired
	private SSORespone ssoRespone;

	/**
	 * This Method handles Customized runtime exception.
	 */

	@ExceptionHandler(SSOException.class)
	@ResponseBody
	public SSORespone handleSSOException(SSOException exception) {

		LOGGER.info(" <=========== SSO exception cause "
				+ exception.getMessage() + " =================> \n");

		ssoRespone.setStatus(exception.getMessage());

		return ssoRespone;

	}

	/**
	 * This Method handles InterruptedException exception.
	 */

	@ExceptionHandler(InterruptedException.class)
	@ResponseBody
	public SSORespone HandleInterruptedException(InterruptedException exception) {

		LOGGER.info(" <=========== SSO InterruptedException  "
				+ exception.getMessage() + " =================> \n");

		ssoRespone.setStatus(exception.getMessage());
		return ssoRespone;

	}

	/**
	 * This Method handles ExecutionException exception.
	 */
	@ExceptionHandler(ExecutionException.class)
	@ResponseBody
	public SSORespone HandleExecutionException(ExecutionException exception) {

		LOGGER.info(" <=========== SSO ExecutionException  "
				+ exception.getMessage() + " =================> \n");

		ssoRespone.setStatus(exception.getMessage());

		return ssoRespone;

	}

	/**
	 * This Method handles General Exception .
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public SSORespone HandleException(Exception exception) {

		LOGGER.info(" <=========== SSO Exception  " + exception.getMessage()
				+ " =================> \n");

		ssoRespone.setStatus(exception.getMessage());
		return ssoRespone;

	}
}
