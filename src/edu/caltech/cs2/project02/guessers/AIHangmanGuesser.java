package edu.caltech.cs2.project02.guessers;

import edu.caltech.cs2.project02.interfaces.IHangmanGuesser;

import java.util.*;

public class AIHangmanGuesser implements IHangmanGuesser {
  private static String dict = "";


  @Override
  public char getGuess(String pattern, Set<Character> guesses) {
    //loop through map from countOccurrences, comparing each value to max, return new max
    /*int max = 0;
    Map letterMap = new TreeMap<Character, Integer>();
    for (Character k : letterMap.keySet()) {
      if (letterMap.get(k) > max)
        max = letterMap.get(k);
    }
    return max;*/
    return 0;
  }
}
//USE LISTS AND MAPS

  //returns the pattern of a
  /*private boolean matchesPattern(String word, String pattern) {
    //char[] patternArr = new Array
    for (char c : word.toCharArray()) {
      if
    }

    return ;
  }

  //return arraylist of words following the pattern
  private ArrayList<String> getPatternSet(String pattern) {
    ArrayList<String> words = new ArrayList<>();
    //go through dictionary and add to words if follows pattern

    return words;
  }

  //return a map relating each letter to the number of occurrences in remaining words
  private Map countOccurrences(Set<Character> guesses) {
  //loop through letters --> if unused, loop through possible words and increment count for each occurrence
    String alpha = "abcdefghijklmnopqrstuvwxyz";
    Map letterMap = new TreeMap<Character, Integer>();
    for (char letter : alpha) {
      if !guesses.contains(letter) { //letter not yet guessed


      }

    }

    return letterMap;
  }*/
