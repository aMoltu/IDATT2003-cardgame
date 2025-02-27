package edu.ntnu.idi.idatt;

import java.util.ArrayList;
import java.util.Random;

public class DeckOfCards {
  private final char[] suit = {'S', 'H', 'D', 'C'};
  ArrayList<String> cards;

  public DeckOfCards() {
    fillCardStock();
  }

  public void fillCardStock(){
    cards = new ArrayList<>();
    for (char type : suit) {
      for (int i = 1; i < 14; i++){
        cards.add(String.valueOf(type+i));
      }
    }
  }

  public ArrayList<String> dealHand(int n){
    fillCardStock();
    ArrayList<String> hand = new ArrayList<>();
    Random rand = new Random();
    for (int i = 0; i < n; i++) {
      int idx = rand.nextInt(cards.size());
      hand.add(cards.get(idx));
      cards.remove(idx);
    }
    return hand;
  }
}
