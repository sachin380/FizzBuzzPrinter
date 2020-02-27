package com.sachin;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.stream.Collectors.joining;

class FizzBuzzGeneratorImpl implements FizzBuzzGenerator {

  private static final int THREE = 3;
  private static final int FIVE = 5;
  public static final String NEW_LINE = "\n";

  private final Function<Integer, Predicate<Integer>> isNumMultipleOfX =
      divisor -> dividend -> dividend % divisor == 0;
  private final Predicate<Integer> isNumMultipleOf3 = isNumMultipleOfX.apply(THREE);
  private final Predicate<Integer> isNumMultipleOf5 = isNumMultipleOfX.apply(FIVE);

  @Override
  public String generateUpto(int number) {
    checkArgument(number > 0, "Number [%s] is negative or zero", number);
    return IntStream.rangeClosed(1, number)
        .mapToObj(this::convertToFizzBuzz)
        .collect(joining(NEW_LINE));
  }

  @Override
  public String generateUpto100() {
    return generateUpto(100);
  }

  String convertToFizzBuzz(int number) {
    boolean multipleOf3 = isNumMultipleOf3.test(number);
    boolean multipleOf5 = isNumMultipleOf5.test(number);
    if (multipleOf3 && multipleOf5) {
      return "FizzBuzz";
    }
    if (multipleOf3) {
      return "Fizz";
    }
    if (multipleOf5) {
      return "Buzz";
    }
    return String.valueOf(number);
  }
}
