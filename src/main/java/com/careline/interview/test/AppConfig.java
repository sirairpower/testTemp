package com.careline.interview.test;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author jack.wang
 */
@SpringBootApplication
@ComponentScan("com.careline.interview.test")
public class AppConfig {

  @Bean
  public PasswordEncoder encoder() {
    return new BCryptPasswordEncoder();
  }

  // <editor-fold desc="Getters & Setters" defaultstate="collapsed">
  // </editor-fold>
}
