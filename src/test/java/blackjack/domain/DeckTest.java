package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeckTest {
    @Test
    @DisplayName("카드 분배 확인")
    void checkDealCards() {
        Deck deck = new Deck();
        Card card = deck.choiceCard(0);
        assertEquals(deck.dealCard(), card);
    }

    @Test
    @DisplayName("카드 삭제 확인")
    void checkRemoveCard() {
        Deck deck = new Deck();
        deck.dealCard();
        assertEquals(deck.getSize(), 51);
    }

    @Test
    void checkIndex() {
        Deck deck = new Deck();
        Card card = deck.choiceCard(1);
        deck.dealCard();
        assertEquals(card, deck.choiceCard(0));
    }
}
