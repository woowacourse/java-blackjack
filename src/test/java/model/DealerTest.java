package model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("딜러는 카드 덱의 핸드가 16이하이면 hit할 수 있다.")
    void canHit_ShouldReturnTrue_WhenCardDeckIsNotBust() {
        CardDeck deck = new CardDeck();
        deck.addCard(Card.from(Number.TEN, Emblem.SPADE));
        deck.addCard(Card.from(Number.SIX, Emblem.CLUB));
        Participant participant = new Dealer(new Name("name"), deck);

        assertTrue(participant.canHit());
    }

    @Test
    @DisplayName("딜러는 카드 덱의 핸드가 16을 초과하면 hit 할 수 없다.")
    void canHit_ShouldReturnFalse_WhenCardDeckIsBust() {
        CardDeck deck = new CardDeck();
        deck.addCard(Card.from(Number.TEN, Emblem.SPADE));
        deck.addCard(Card.from(Number.SEVEN, Emblem.CLUB));
        Participant participant = new Dealer(new Name("name"), deck);

        assertFalse(participant.canHit());
    }
}
