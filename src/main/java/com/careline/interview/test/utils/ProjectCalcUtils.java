package com.careline.interview.test.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ProjectCalcUtils {

  public static BigDecimal avg(List<Integer> operands, int scale) {
    return sum(operands).divide(new BigDecimal(operands.size()), scale, RoundingMode.HALF_UP);
  }

  public static BigDecimal sum(List<Integer> operands) {
    return operands.stream().map(BigDecimal::valueOf).reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  public static <E> List<E> filterNull(List<E> operands) {
    return operands.stream().filter(e -> null != e).collect(Collectors.toList());
  }

  public static void main(String[] args) {
    System.out.println(sum(filterNull(Arrays.asList(2, 6, null, 43, 121, 16))));
    System.out.println(avg(filterNull(Arrays.asList(2, 6, null, 43, 121, 16)), 2));
    System.out.println(filterNull(Arrays.asList(2, 6, null, 43, 121, 16)).stream().sorted().collect(Collectors.toList()));


  }

}
