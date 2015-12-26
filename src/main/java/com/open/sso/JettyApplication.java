package com.open.sso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This class is entry point for whole application
 * 
 * @author vineetkarandikar@gmail.com
 * @version 1.0
 * @since 2015-26-12
 *
 */

@SpringBootApplication
public class JettyApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(JettyApplication.class, args);
	}

}
