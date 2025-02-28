package edu.ntnu.idi.idatt;

import java.util.ArrayList;
import java.util.Random;

public class DeckOfCards {
  private final char[] suit = {'S', 'H', 'D', 'C'};
  ArrayList<PlayingCard> cards;

  public DeckOfCards() {
    fillCardStock();
  }

  public void fillCardStock(){
    cards = new ArrayList<>();
    for (char s : suit) {
      for (int r = 1; r < 14; r++){
        cards.add(new PlayingCard(s, r));
      }
    }
  }

  public HandOfCards dealHand(int n){
    fillCardStock();
    HandOfCards hand = new HandOfCards();
    Random rand = new Random();
    for (int i = 0; i < n; i++) {
      int idx = rand.nextInt(cards.size());
      hand.add(cards.get(idx));
      cards.remove(idx);
    }
    return hand;
  }
}
