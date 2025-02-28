package edu.ntnu.idi.idatt;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

class PlayingCardTest {

  @Test
  void testValidCardCreation() {
    PlayingCard card = new PlayingCard('H', 10);
    assertEquals('H', card.getSuit());
    assertEquals(10, card.getFace());
  }

  @Test
  void testInvalidSuitThrowsException() {
    assertThrows(IllegalArgumentException.class, () -> new PlayingCard('X', 5));
  }

  @Test
  void testInvalidFaceThrowsException() {
    assertThrows(IllegalArgumentException.class, () -> new PlayingCard('H', 0));
    assertThrows(IllegalArgumentException.class, () -> new PlayingCard('H', 14));
  }

  @Test
  void testGetAsString() {
    PlayingCard card = new PlayingCard('D', 1);
    assertEquals("D1", card.getAsString());
  }

  @Test
  void testCardEquality() {
    PlayingCard card1 = new PlayingCard('S', 7);
    PlayingCard card2 = new PlayingCard('S', 7);
    PlayingCard card3 = new PlayingCard('C', 7);
    assertEquals(card1, card2);
    assertNotEquals(card1, card3);
  }
}

