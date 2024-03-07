package model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("딜러는 카드 덱의 핸드가 16이하이면 hit할 수 있다.")
    void canHit_ShouldReturnTrue_WhenCardDeckIsNotBust() {
        Participant dealer = new Dealer(new Name("name"));

        dealer.hitCard(Card.from(Number.TEN, Emblem.SPADE));
        dealer.hitCard(Card.from(Number.SIX, Emblem.HEART));

        assertTrue(dealer.canHit());
    }

    @Test
    @DisplayName("딜러는 카드 덱의 핸드가 16을 초과하면 hit 할 수 없다.")
    void canHit_ShouldReturnFalse_WhenCardDeckIsBust() {

        Participant dealer = new Dealer(new Name("name"));
        dealer.hitCard(Card.from(Number.TEN, Emblem.SPADE));
        dealer.hitCard(Card.from(Number.SEVEN, Emblem.HEART));

        assertFalse(dealer.canHit());
    }
}
