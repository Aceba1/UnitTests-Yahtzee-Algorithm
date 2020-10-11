package com.aceba1.yahtzee;

import com.aceba1.util.Die;

public class HoldableDie extends Die {

  private final static int RANGE = 6;
  private int rolled;
  private boolean hold;

  public HoldableDie() {
    this.rolled = 0;
  }

  public void toggleHold() {
    hold = !hold;
  }

  @Override
  public int roll() {
    if (hold)
      return rolled;
    rolled = roll(RANGE);
    return rolled;
  }

  public int getRolled() {
    if (rolled == 0)
      return roll();
    return rolled;
  }

  @Override
  public String toString() {
    return getRolled() + (hold ? " (held)":"");
  }
}
