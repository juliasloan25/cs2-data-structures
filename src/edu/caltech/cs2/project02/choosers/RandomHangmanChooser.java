package edu.caltech.cs2.project02.choosers;

import edu.caltech.cs2.project02.interfaces.IHangmanChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class RandomHangmanChooser implements IHangmanChooser {
  private int guessesLeft;
  private ArrayList<Character> guesses;
  private final String word;
  private static Random rand = new Random();

  public RandomHangmanChooser(int wordLength, int maxGuesses) {
    Scanner dict;
    this.guesses = new ArrayList<>();
    try {
      dict = new Scanner(new File("data/scrabble.txt"));
    }
    catch (FileNotFoundException e) {
      throw new IllegalArgumentException("Dictionary file not found.");
    }
    if (wordLength < 1 || maxGuesses < 1)
      throw new IllegalArgumentException("One or both argument(s) too small.");

    this.guessesLeft = maxGuesses;
    boolean lengthExists = false;
    SortedSet<String> dictSet = new TreeSet<String>();
    while (dict.hasNextLine()) {
      String temp = dict.nextLine();
      if (temp.length() == wordLength) { //if not true, lengthExists remains false
        dictSet.add(temp); //creates dictSet of only words of desired length
        lengthExists = true;
      }
    }
    if (!lengthExists) //no words of given length in dictionary
      throw new IllegalStateException("No words found with the given length.");
    this.word = new ArrayList<>(dictSet).get(rand.nextInt(dictSet.size()));
  }


  @Override
  //Returns the number of occurrences of the guessed letter in the secret word.
  public int makeGuess(char letter) {
    if (this.guessesLeft <= 0)
      throw new IllegalStateException("No moves left.");
    if (this.guesses.contains(letter))
        throw new IllegalArgumentException("This letter was already guessed.");
    if (!Character.isLowerCase(letter))
      throw new IllegalArgumentException("Guesses must be lowercase.");
    guesses.add(letter);
    int numInWord = 0;
    for (int i = 0; i < word.length(); i++) {
      if (letter == word.charAt(i))
        numInWord++;
    }
    if (numInWord == 0)
      this.guessesLeft--;
    return numInWord;
  }


  @Override
  //Returns true if there are no guesses remaining or the word has been guessed, and false otherwise;
  public boolean isGameOver() {
    int numGuessed = 0;
    if (this.getGuessesRemaining() <= 0)
      return true;
    for (int i = 0; i < this.word.length(); i++) {
      for (int j = 0; j < guesses.size(); j++) {
        if (word.charAt(i) == guesses.get(j))
          numGuessed++;
      }
    }
    if (numGuessed == this.word.length())
      return true;
    return false;
  }

  @Override
  //Returns a string that reveals the correctly guessed letters in their positions.
  public String getPattern() {
    String display = "";
    for (char ch : this.word.toCharArray()) {
      if (guesses.contains(ch))
        display += ch;
      else
        display += '-';
    }
    return display;
  }

  @Override
  //Returns a SortedSet of the letters guessed so far.
  public SortedSet<Character> getGuesses() {
      SortedSet<Character> guessSet = new TreeSet<Character>();
      for (int i = 0; i < this.guesses.size(); i++) {
        guessSet.add(this.guesses.get(i));
    }
    return guessSet;
  }

  @Override
  //Returns the number of guesses left in the game.
  public int getGuessesRemaining() {
    return this.guessesLeft;
  }

  @Override
  //Returns the secret word and ends the game.
  public String getWord() {
    this.guessesLeft = 0;
    return this.word;
  }
}