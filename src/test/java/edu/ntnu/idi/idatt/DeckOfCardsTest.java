package edu.ntnu.idi.idatt;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

class DeckOfCardsTest {
  private DeckOfCards deck;

  @BeforeEach
  void setUp() {
    deck = new DeckOfCards();
  }

  @Test
  void testDeckSizeAfterFill() {
    assertEquals(52, deck.cards.size());
  }

  @Test
  void testDealHand() {
    int handSize = 5;
    deck.dealHand(handSize);
    assertEquals(47, deck.cards.size());
    assertEquals(handSize, deck.dealHand(handSize).size());
  }
}
