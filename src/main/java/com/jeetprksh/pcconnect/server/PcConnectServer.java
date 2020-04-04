package com.jeetprksh.pcconnect.server;

import com.jeetprksh.pcconnect.server.entity.ServerParams;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

/*
 * @author Jeet Prakash
 * */
@SpringBootApplication
public class PcConnectServer {

  private static Logger logger = Logger.getLogger(PcConnectServer.class.getName());

  private static final String DEFAULT_APP_PORT = "8088";
  private static ConfigurableApplicationContext context;
  private static List<String> sharedDirectories = new ArrayList<>();

  public static void main(String[] args) {
    ServerParams params = new ServerParams(DEFAULT_APP_PORT, new ArrayList<>());
    start(params);
  }

  public static void start(ServerParams serverParams) {
    logger.info("Starting server at default port");
    verifyPort(serverParams.getServerPort());
    sharedDirectories = serverParams.getSharedDirectories();
    if (Objects.isNull(context) || !context.isActive()) {
      SpringApplication application = new SpringApplication(PcConnectServer.class);
      application.setDefaultProperties(Collections.singletonMap("server.port", serverParams.getServerPort()));
      context = application.run();
    }
  }

  public static void restart(ServerParams serverParams) {
    logger.info("Restarting server at port " + serverParams.getServerPort());
    verifyPort(serverParams.getServerPort());
    Thread thread = new Thread(() -> {
      stop();
      start(serverParams);
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

  public static List<String> getSharedDirectories() {
    return sharedDirectories;
  }
}
