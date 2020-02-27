package com.sachin;

public class Application {

  public static void main(String[] args) {
    FizzBuzzGenerator fizzBuzzGenerator = new FizzBuzzGeneratorImpl();
    System.out.println(fizzBuzzGenerator.generateUpto100());
  }
}
