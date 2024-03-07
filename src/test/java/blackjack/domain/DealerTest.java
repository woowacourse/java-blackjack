package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DealerTest {

    @DisplayName("딜러는 자신의 패가 16이하이면 한장을 더 받는다")
    @Test
    void should_AddCard_When_HandsScoreBelowThreshold() {
        Dealer dealer = new Dealer();
        Deck deck = Deck.createSuffledDeck();
        dealer.addCard(Card.create(9));
        dealer.addCard(Card.create(5));

        assertTrue(dealer.draw(deck));
    }

    @DisplayName("딜러는 자신의 패가 17이상이면 한장을 더 받지 않는다")
    @Test
    void should_NotAddCard_When_HandsScoreOverThreshold() {
        Dealer dealer = new Dealer();
        Deck deck = Deck.createSuffledDeck();
        dealer.addCard(Card.create(9));
        dealer.addCard(Card.create(6));

        assertFalse(dealer.draw(deck));
    }
}
