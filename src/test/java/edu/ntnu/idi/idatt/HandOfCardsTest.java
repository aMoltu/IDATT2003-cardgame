package edu.ntnu.idi.idatt;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

class HandOfCardsTest {
  @Test
  void testGetSum() {
    HandOfCards hand = new HandOfCards();
    hand.add(new PlayingCard('H', 5));
    hand.add(new PlayingCard('S', 10));
    assertEquals(15, hand.getSum());
  }

  @Test
  void testGetHearts() {
    HandOfCards hand = new HandOfCards();
    hand.add(new PlayingCard('H', 3));
    hand.add(new PlayingCard('S', 7));
    HandOfCards hearts = hand.getHearts();
    assertEquals(1, hearts.size());
    assertEquals('H', hearts.getFirst().getSuit());
  }

  @Test
  void testHasQueenOfSpades() {
    HandOfCards hand = new HandOfCards();
    hand.add(new PlayingCard('S', 12));
    assertTrue(hand.hasQueenOfSpades());
  }

  @Test
  void testIsFlush() {
    HandOfCards hand = new HandOfCards();
    hand.add(new PlayingCard('C', 2));
    hand.add(new PlayingCard('C', 5));
    hand.add(new PlayingCard('C', 8));
    hand.add(new PlayingCard('C', 10));
    hand.add(new PlayingCard('C', 12));
    assertTrue(hand.isFlush());
  }
}
