package edu.ntnu.idi.idatt;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class HandOfCards extends ArrayList<PlayingCard> {
  private final char[] suit = {'S', 'H', 'D', 'C'};

  public int getSum(){
    return this.stream().mapToInt(PlayingCard::getFace).sum();
  }

  public HandOfCards getHearts() {
    return this.stream()
        .filter(card -> card.getSuit() == 'H')
        .collect(Collectors.toCollection(HandOfCards::new));
  }

  public boolean hasQueenOfSpades() {
    PlayingCard queenOfSpades = new PlayingCard('S', 12);
    return this.contains(queenOfSpades);
  }

  public boolean isFlush() {
    for (char s : suit) {
      if (this.stream().filter(card -> card.getSuit() == s).count() == 5){
        return true;
      }
    }
    return false;
  }

  public String getAsString() {
    StringBuilder sb = new StringBuilder();
    boolean first = true;
    for (PlayingCard card : this) {
      if (!first) {
        sb.append(", ");
      } else {
        first = false;
      }
      sb.append(card.getAsString());
    }
    return sb.toString();
  }
}
