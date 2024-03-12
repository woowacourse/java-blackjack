package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;

import blackjack.domain.deck.Card;
import blackjack.domain.deck.Deck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @DisplayName("딜러는 자신의 패가 16이하이면 한장을 더 받는다")
    @Test
    void should_AddCard_When_HandsScoreBelowThreshold() {
        Dealer dealer = new Dealer();
        Deck deck = Deck.createShuffledDeck();
        dealer.addCard(Card.create(9));
        dealer.addCard(Card.create(5));

        dealer.addCard(deck.draw());

        assertThat(dealer.getHandsCards()).hasSize(3);
    }

    @DisplayName("딜러는 자신의 패가 17이상이면 한장을 더 받지 않는다")
    @Test
    void should_NotAddCard_When_HandsScoreOverThreshold() {
        Dealer dealer = new Dealer();
        Deck deck = Deck.createShuffledDeck();
        dealer.addCard(Card.create(9));
        dealer.addCard(Card.create(6));

        assertFalse(dealer.draw(deck));
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

    @DisplayName("딜러는 초기카드 분배 후에 패를 보여줄 수 있다.")
    @Test
    void should_Unsupported_NotHasInitialCards() {
        Dealer dealer = new Dealer();
        assertThatThrownBy(dealer::getFirstCardName)
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("초기 카드 분배 후에 사용할 수 있습니다.");
    }

}
