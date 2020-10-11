package com.aceba1.yahtzee;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

  final static Scanner in = new Scanner(System.in);
  final static List<Die> dice = new ArrayList<>();

  public static void main(String[] args) {
    // Variables are initialized by static constructor
    System.out.println("Max 5 Dice\nStart of program");
    boolean preMessage = true;
    for (int i = 0; i < 5; i++) {
      Die currentDie = new Die(6, 3);
      dice.add(currentDie);

      boolean canReroll;
      do {
        canReroll = printDice();
        if (!canReroll) System.out.println("\nOut of rerolls!");
        if (preMessage) preMessage = preRollMessage();
      }
      while (canReroll && promptReroll());
    }
    System.out.println("End of program");
  }

  static boolean preRollMessage() {
    System.out.print("\nEnter an index to reroll, or 0 to continue");
    return false;
  }

  static boolean printDice() {
    System.out.println("\nDice: " + dice.size() +
      ", Sum=" + dice.stream()
        .mapToInt(Die::getRolled)
        .sum());

    for (int i = 0; i < dice.size(); i++)
      System.out.println("- " + (i + 1) + ": " + dice.get(i));

    return dice.stream()
      .mapToInt(Die::getRollsLeft)
      .sum() != 0;
  }

  static boolean promptReroll() {
    int size = dice.size();
    int input = prompt("\nReroll? " +
        "[0," + (size > 1 ? "1-" : "") + size + "]: ",
      0, dice.size());
    if (input == 0)
      return false;

    var die = dice.get(input - 1);

    if (die.getRollsLeft() == 0)
      System.out.println("Out of rerolls!");
    else die.reroll();

    return true;
  }

  static int prompt(String textNoLine, int minValue, int maxValue) {
    while (true) {
      System.out.print(textNoLine);
      try {
        int val = in.nextInt();
        in.nextLine();

        if (val > maxValue || val < minValue)
          System.out.println("Out of range (" + minValue + "-" + maxValue + ")");
        else
          return val;
      } catch (InputMismatchException e) {
        System.out.println("Not a number");
        in.nextLine(); // Clear buffer to avoid locking
      }
    }
  }
}
