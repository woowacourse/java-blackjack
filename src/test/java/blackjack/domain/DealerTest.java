package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    @DisplayName("딜러는 자신의 패가 16이하이면 한장을 더 받는다")
    @Test
    void should_AddCard_When_HandsScoreBelowThreshold() {
        Dealer dealer = new Dealer();
        Deck deck = Deck.createSuffledDeck();
        dealer.addCard(Card.create(9));
        dealer.addCard(Card.create(5));

        dealer.draw(deck);

        assertThat(dealer.getHandsCards()).hasSize(3);
    }

    @DisplayName("딜러는 자신의 패가 17이상이면 한장을 더 받지 않는다")
    @Test
    void should_NotAddCard_When_HandsScoreOverThreshold() {
        Dealer dealer = new Dealer();
        Deck deck = Deck.createSuffledDeck();
        dealer.addCard(Card.create(9));
        dealer.addCard(Card.create(6));

        dealer.draw(deck);

        assertThat(dealer.getHandsCards()).hasSize(2);
    }

    @DisplayName("딜러는 첫번째 패를 보여준다")
    @Test
    void should_ShowFirstCard() {
        Dealer dealer = new Dealer();
        dealer.addCard(Card.create(6));
        dealer.addCard(Card.create(45));

        assertThat(dealer.getFirstCardName())
                .isEqualTo(Card.create(6)
                        .getCardName());
    }

}
