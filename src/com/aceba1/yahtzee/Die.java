package com.aceba1.yahtzee;

import java.util.Random;

public class Die {
  private static Random random = new Random();

  private final int range;
  private int rolled;

  public Die() {
    this(6);
  }

  public Die(int range) {
    this.range = range;
    this.rolled = 0;
  }

  public int getRolled() {
    if (rolled == 0)
      return reroll();
    return rolled;
  }

  public int reroll() {
    rolled = random.nextInt(range) + 1;
    return rolled;
  }

  @Override
  public String toString() {
    return "d" + range +
      ": " + getRolled();
  }
}
