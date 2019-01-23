package edu.caltech.cs2.project02.choosers;

import edu.caltech.cs2.project02.interfaces.IHangmanChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class EvilHangmanChooser implements IHangmanChooser {
  private int guessesLeft;
  private ArrayList<Character> guesses;
  private static Random rand = new Random();
  private TreeSet<String> words;
  private String pattern = "";

  public EvilHangmanChooser(int wordLength, int maxGuesses) {
    Scanner dict;
    this.guesses = new ArrayList<>();
    try {
      dict = new Scanner(new File("data/scrabble.txt")); //set dict = dictionary file
    }
    catch (FileNotFoundException e) {
      throw new IllegalArgumentException("Dictionary file not found.");
    }
    if (wordLength < 1 || maxGuesses < 1)
      throw new IllegalArgumentException("One or both argument(s) too small.");

    this.guessesLeft = maxGuesses;
    boolean lengthExists = false;
    SortedSet<String> dictSet = new TreeSet<String>(); //new SortedSet to store words of desired length
    while (dict.hasNextLine()) {
      String temp = dict.nextLine();
      if (temp.length() == wordLength) { //if not true, lengthExists remains false and exception is thrown
        dictSet.add(temp); //adds all words of desired length to dictSet
        lengthExists = true;
      }
    }
    if (!lengthExists) //no words of given length
      throw new IllegalStateException("No words found with the given length.");
    //this.word = new ArrayList<>(dictSet).get(rand.nextInt(dictSet.size())); //CHANGE
    //call other methods?


    //getPatternSet(getPatternMap(getPattern(), dictSet)); //tf is this
  }

  @Override
  public int makeGuess(char letter) {
    if (this.guessesLeft <= 0)
      throw new IllegalStateException("No moves left.");
    if (this.guesses.contains(letter))
      throw new IllegalArgumentException("This letter was already guessed.");
    if (!Character.isLowerCase(letter))
      throw new IllegalArgumentException("Guesses must be lowercase.");

    Map<String, TreeSet<String>> patterns = new TreeMap<String, TreeSet<String>>();
    guesses.add(letter);
    int numWords = 0;
    boolean validGuess = false;
    String tempKey = this.pattern; //used to create new keys to add to map

    for (String k : patterns.keySet()) {
      for (String v : patterns.get(k)) {
        for (int i = 0; i < v.length(); i++) {
          if (letter == v.charAt(i)) { //letter in at least 1 word
            validGuess = true;        //NEED TO ADD NEW KEYS TO MAP IF VALIDGUESS - DEPENDS ON WHERE CHAR IS IN WORDS(v)
            tempKey.toCharArray()[i] = letter; //create new pattern tempKey
            //tempKey.toString();
            patterns.put(tempKey, new TreeSet<String>());
            patterns.get(tempKey).add(v); //add this word to the map at key tempKey
          }
        }

        if (patterns.get(k).size() > numWords) {
          numWords = patterns.get(k).size();
          this.pattern = k;
        }
      }
    }

    if (!validGuess) //incorrect guess
      this.guessesLeft--;
    return numWords;
  }

  @Override
  //Returns true if there are no guesses remaining or the word has been guessed, and false otherwise;
  public boolean isGameOver() {
    int numGuessed = 0;
    if (this.getGuessesRemaining() <= 0 || !this.pattern.contains("-"))
      return true;
    else
      return false;
  }

  @Override
  //Returns the current pattern.
  public String getPattern() {
    return this.pattern;
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

  //Returns the number of guesses remaining.
  public int getGuessesRemaining() {
    return this.guessesLeft;
  }

  @Override
  //Returns the secret word and ends the game.
  public String getWord() {
    this.guessesLeft = 0;
    //return this.patterns.get(this.pattern).first();
    return "";
  }



  /*//Returns a TreeMap containing all possible patterns mapped to the set of words with that pattern.
  private Map getPatternMap(String pattern, SortedSet<String> dictSet) {
    Map wordMap = new TreeMap<String, SortedSet<String>>(); //change to sortedset for alphabetical order
    ArrayList<String> dictArray  = new ArrayList<>(dictSet); //convert dictSet from SortedSet to ArrayList
    for (String compare : dictArray) {
      /*if (wordMap.containsKey(getPattern(compare)))
        wordMap.get(getPattern(compare)).add(compare);
      else
        wordMap.put(getPattern(compare), compare);*/
    /*}
    return wordMap;
  }


  //Sets the field words to be the set of words with the most common pattern.
  private void getPatternSet(Map wordMap<String, SortedSet<String>>) {
    int max = 0;
    String currentKey = "";
    //TreeSet<String> wordSet = new TreeSet<String>();
    for (String k : wordMap.keySet()) {
      if (wordMap.get(k).size() > max || (wordMap.get(k).size() == max && k.compareTo(currentKey) < 0)) {
        currentKey = k;
        this.words = wordMap.get(k);
      }
    }
  }*/

}