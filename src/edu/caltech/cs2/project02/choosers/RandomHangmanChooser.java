package edu.caltech.cs2.project02.choosers;

import edu.caltech.cs2.project02.interfaces.IHangmanChooser;

import java.util.*;

public class RandomHangmanChooser implements IHangmanChooser {

  @Override
  public int makeGuess(char letter) {
    return 0;
  }

  @Override
  public boolean isGameOver() {
    return false;
  }

  @Override
  public String getPattern() {
    return null;
  }

  @Override
  public SortedSet<Character> getGuesses() {
    return null;
  }

  @Override
  public int getGuessesRemaining() {
    return 0;
  }

  @Override
  public String getWord() {
    return null;
  }
}