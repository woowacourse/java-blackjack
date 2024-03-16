package model.participant;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import model.card.Card;
import model.card.Emblem;
import model.card.Number;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("딜러는 카드 덱의 핸드가 16이하이면 hit할 수 있다.")
    void canHit_ShouldReturnTrue_WhenCardDeckIsNotBust() {
        Card card1 = Card.from(Number.TEN, Emblem.SPADE);
        Card card2 = Card.from(Number.SIX, Emblem.HEART);
        Participant dealer = new Dealer(card1, card2);

        assertTrue(dealer.canHit());
    }

    @Test
    @DisplayName("딜러는 카드 덱의 핸드가 16을 초과하면 hit 할 수 없다.")
    void canHit_ShouldReturnFalse_WhenCardDeckIsBust() {
        Card card1 = Card.from(Number.TEN, Emblem.SPADE);
        Card card2 = Card.from(Number.SEVEN, Emblem.HEART);

        Participant dealer = new Dealer(card1, card2);

        assertFalse(dealer.canHit());
    }

}
