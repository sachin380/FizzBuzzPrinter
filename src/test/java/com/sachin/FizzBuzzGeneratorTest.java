package com.sachin;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.params.provider.Arguments.of;

public class FizzBuzzGeneratorTest {

  private FizzBuzzGeneratorImpl fizzBuzzGenerator = new FizzBuzzGeneratorImpl();

  @ParameterizedTest(name = "{index}) {0} should be converted to \"{1}\"")
  @MethodSource("createNumbersWithExpectedConvertedNumbers")
  @DisplayName(
      "Should convert a given number to Fizz if multiple of 3 or Buzz if multiple of 5 or "
          + "FizzBuzz if multiple of 3 & 5 otherwise return original number")
  void shouldConvertNumberToFizzBuzzIfAppropriate(Integer number, String expectedConvertedNumber) {
    assertThat(fizzBuzzGenerator.convertToFizzBuzz(number)).isEqualTo(expectedConvertedNumber);
  }

  @Test
  @DisplayName(
      "Should generate new line separated numbers upto 10 with Fizz Buzz Conversion Applied")
  void shouldGenerateFizzBuzzNumberUpto10() {
    assertThat(fizzBuzzGenerator.generateUpto(10))
        .isEqualTo("1\n2\nFizz\n4\nBuzz\nFizz\n7\n8\nFizz\nBuzz");
  }

  @Test
  @DisplayName("Should throw Illegal Argument Exception if number is negative or zero")
  void shouldThrowIAEIfNumberIsNegativeOrZero() {
    assertThatIllegalArgumentException()
        .isThrownBy(() -> fizzBuzzGenerator.generateUpto(0))
        .withMessage("Number [0] is negative or zero");
    assertThatIllegalArgumentException()
        .isThrownBy(() -> fizzBuzzGenerator.generateUpto(-100))
        .withMessage("Number [-100] is negative or zero");
  }

  private static Stream<Arguments> createNumbersWithExpectedConvertedNumbers() {
    return Stream.of(
        of(1, "1"),
        of(2, "2"),
        of(3, "Fizz"),
        of(4, "4"),
        of(5, "Buzz"),
        of(6, "Fizz"),
        of(7, "7"),
        of(8, "8"),
        of(9, "Fizz"),
        of(10, "Buzz"),
        of(11, "11"),
        of(12, "Fizz"),
        of(13, "13"),
        of(14, "14"),
        of(15, "FizzBuzz"),
        of(16, "16"),
        of(17, "17"),
        of(18, "Fizz"),
        of(19, "19"),
        of(20, "Buzz"));
  }
}
