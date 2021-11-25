package com.careline.interview.test.utils;

import java.time.format.DateTimeFormatter;

public class ProjectDateUtils {

  private static DateTimeFormatter DEAFAULT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

  public static DateTimeFormatter getDeafaultFormatter() {
    return DEAFAULT_FORMATTER;
  }

}
