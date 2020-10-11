package com.aceba1.yahtzee;

import java.util.Random;

public class Die {
  private final static Random random = new Random();

  private final int range;
  private int rolled;
  private int rollsLeft;

  public Die(int range, int rollsLeft) {
    this.range = range;
    this.rolled = 0;
    this.rollsLeft = rollsLeft;
  }

  public int getRolled() {
    if (rolled == 0)
      return reroll();
    return rolled;
  }

  public int getRollsLeft() {
    return rollsLeft;
  }

  public int reroll() {
    if (rollsLeft <= 0)
      return rolled;
    rollsLeft--;
    rolled = random.nextInt(range) + 1;
    return rolled;
  }

  @Override
  public String toString() {
    return getRolled() + " (r" + rollsLeft + ")";
  }
}
