package com.careline.interview.test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

public class Main {

  public static void main(String[] args) throws UnknownHostException {
    ConfigurableApplicationContext application = SpringApplication.run(AppConfig.class, args);
    Environment env = application.getEnvironment();
    System.out.println("host start on -> \t http://" + InetAddress.getLocalHost().getHostAddress() + ":" + env.getProperty("server.port"));
  }
}
