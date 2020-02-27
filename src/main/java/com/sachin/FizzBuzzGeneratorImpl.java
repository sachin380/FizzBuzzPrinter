package com.sachin;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.stream.Collectors.joining;

class FizzBuzzGeneratorImpl implements FizzBuzzGenerator {

  private static final int THREE = 3;
  private static final int FIVE = 5;
  public static final String NEW_LINE = "\n";

  private final Predicate<Integer> numContains3Predicate;
  private final Predicate<Integer> numContains5Predicate;
  private final Predicate<Integer> divisibleBy3Predicate;
  private final Predicate<Integer> divisibleBy5Predicate;

  public FizzBuzzGeneratorImpl() {
    Function<Integer, Predicate<Integer>> regExToFindXInNumFunc =
        x -> num -> Pattern.compile(String.format(".*%s.*", x)).matcher(num.toString()).matches();

    Function<Integer, Predicate<Integer>> divisibleByXFunc =
        divisor -> dividend -> dividend % divisor == 0;

    numContains3Predicate = regExToFindXInNumFunc.apply(THREE);
    numContains5Predicate = regExToFindXInNumFunc.apply(FIVE);
    divisibleBy3Predicate = divisibleByXFunc.apply(THREE);
    divisibleBy5Predicate = divisibleByXFunc.apply(FIVE);
  }

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
    boolean divisibleByOrHas3 = divisibleBy3Predicate.or(numContains3Predicate).test(number);
    boolean divisibleByOrHas5 = divisibleBy5Predicate.or(numContains5Predicate).test(number);
    if (divisibleByOrHas3 && divisibleByOrHas5) {
      return "FizzBuzz";
    }
    if (divisibleByOrHas3) {
      return "Fizz";
    }
    if (divisibleByOrHas5) {
      return "Buzz";
    }
    return String.valueOf(number);
  }
}
