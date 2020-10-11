package com.aceba1.util;

import java.util.Random;

public abstract class Die {
  private final static Random random = new Random();

  public abstract int roll();

  protected final static int roll(int range) {
    return random.nextInt(range) + 1;
  }
}
