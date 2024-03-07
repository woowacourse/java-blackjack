package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DealerTest {

    @DisplayName("딜러는 자신의 패가 16이하이면 한장을 더 받겠다고 요청한다")
    @Test
    void should_ReturnTrue_When_HandsScoreBelowThreshold() {
        Dealer dealer = new Dealer("딜러");

        dealer.addCard(Card.create(9));
        dealer.addCard(Card.create(5));

        assertTrue(dealer::shouldDraw);
    }

    @DisplayName("딜러는 자신의 패가 17이상이면 카드를 요청하지 않는다")
    @Test
    void should_ReturnFalse_When_HandsScoreOverThreshold() {
        Dealer dealer = new Dealer("딜러");

        dealer.addCard(Card.create(9));
        dealer.addCard(Card.create(6));

        assertFalse(dealer::shouldDraw);
    }

    @DisplayName("딜러는 자신의 패가 16이하이면 한장을 더 받는다")
    @Test
    void should_AddCard_When_HandsScoreBelowThreshold() {
        Dealer dealer = new Dealer("딜러");
        Deck deck = Deck.createSuffledDeck();
        dealer.addCard(Card.create(9));
        dealer.addCard(Card.create(5));

        dealer.decideDraw(dealer::shouldDraw, deck);

        assertThat(dealer.getHandsCards()).hasSize(3);
    }

    @DisplayName("딜러는 자신의 패가 17이상이면 한장을 더 받지 않는다")
    @Test
    void should_NotAddCard_When_HandsScoreOverThreshold() {
        Dealer dealer = new Dealer("딜러");
        Deck deck = Deck.createSuffledDeck();
        dealer.addCard(Card.create(9));
        dealer.addCard(Card.create(6));

        dealer.decideDraw(dealer::shouldDraw, deck);

        assertThat(dealer.getHandsCards()).hasSize(2);
    }
}
