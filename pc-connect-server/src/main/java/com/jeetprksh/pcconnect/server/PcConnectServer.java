package com.jeetprksh.pcconnect.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.security.InvalidParameterException;
import java.util.Collections;
import java.util.Objects;
import java.util.logging.Logger;

/*
 * @author Jeet Prakash
 * */
@SpringBootApplication
public class PcConnectServer {

	private static Logger logger = Logger.getLogger(PcConnectServer.class.getName());

	private static final String DEFAULT_APP_PORT = "8080";

	private static ConfigurableApplicationContext context;

	public static void main(String[] args) {
		start(DEFAULT_APP_PORT);
	}

	public static void start(String port) {
		logger.info("Starting server at default port");
		verifyPort(port);
		if (Objects.isNull(context) || !context.isActive()) {
			SpringApplication application = new SpringApplication(PcConnectServer.class);
			application.setDefaultProperties(Collections.singletonMap("server.port", port));
			context = application.run();
		}
	}

	public static void restart(String port) {
		logger.info("Restarting server at port " + port);
		verifyPort(port);
		Thread thread = new Thread(() -> {
			stop();
			start(port);
		});
		thread.setDaemon(false);
		thread.start();
	}

	public static void stop() {
		if (!Objects.isNull(context)) {
			context.close();
		}
	}

	private static void verifyPort(String port) {
		int intPort = Integer.parseInt(port);
		if (intPort < 0 || intPort > 65535) {
			throw new InvalidParameterException("Invalid Port Number.");
		}
	}
}
