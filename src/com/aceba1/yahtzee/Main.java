package com.aceba1.yahtzee;

import com.aceba1.util.Input;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    while (true) {
      List<HoldableDie> dice = new ArrayList<>();

      for (int i = 0; i < 5; i++)
        dice.add(new HoldableDie());

      preRollMessage();

      for (int i = 2; i >= 1; i--) {
        rollDice(dice);
        printDice(dice, i);

        while (promptHold(dice))
          printDice(dice, i);
      }

      rollDice(dice);
      System.out.println("\nEnd");
      for (int i = 0; i < dice.size(); i++)
        System.out.println("- " + (i + 1) + ": " + dice.get(i).getRolled());

      try {
        Thread.sleep(1500);
      } catch (InterruptedException e) {
        break;
      }
    }
  }

  static void preRollMessage() {
    System.out.print("\nEnter the index of a die to hold\nEnter 0 to reroll\n");
  }

  static void rollDice(List<HoldableDie> dice) {
    System.out.print("\nRolling...\n");
    for (HoldableDie die : dice)
      die.roll();
  }

  static void printDice(List<HoldableDie> dice, int rollsLeft) {
    System.out.println("\nRolls left: " + rollsLeft);

    for (int i = 0; i < dice.size(); i++)
      System.out.println("- " + (i + 1) + ": " + dice.get(i));
  }

  static boolean promptHold(List<HoldableDie> dice) {
    int size = dice.size();
    int input = Input.getNum("\nHold " +
        "[0," + (size > 1 ? "1-" : "") + size + "]: ",
      0, size);

    if (input == 0) return false;

    dice.get(input - 1).toggleHold();
    return true;
  }
}
