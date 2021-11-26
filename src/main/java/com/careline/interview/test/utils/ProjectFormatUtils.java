package com.careline.interview.test.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.UUID;
import java.util.stream.IntStream;

public class ProjectFormatUtils {

  private static DecimalFormat decimalFormat = new DecimalFormat("#,###,###,##0.00");

  public static String formatTwoDecimalPlaces(BigDecimal decimal) {
    return decimalFormat.format(decimal);
  }

  public static String formatTwoDecimalPlaces(Integer integer) {
    return decimalFormat.format(integer);
  }

  public static void main(String[] args) {
    BigDecimal num = new BigDecimal(12.55533);
    System.out.println(formatTwoDecimalPlaces(num));
    IntStream.range(0, 100).forEach(i -> {
      System.out.println(UUID.randomUUID());
    });
  }

}
