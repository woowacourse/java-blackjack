package blackjack.domain.gamer;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;

class DealerTes {

    @Test
    @DisplayName("딜러는 카드 숫자 합이 16 이하이면 카드를 추가로 받을 수 있다")
    void dealerAdditionalCardTest() {
        Card card1 = new Card(CardType.CLOVER, CardNumber.JACK);
        Card card2 = new Card(CardType.CLOVER, CardNumber.TWO);
        Dealer dealer = new Dealer();
        dealer.addCard(card1);
        dealer.addCard(card2);

        assertThat(dealer.canReceiveAdditionalCards()).isTrue();
    }

    @Test
    @DisplayName("딜러는 카드 숫자 합이 16 초과면 카드를 추가로 받을 수 없다")
    void dealerAdditionalCardTest2() {
        Card card1 = new Card(CardType.CLOVER, CardNumber.JACK);
        Card card2 = new Card(CardType.CLOVER, CardNumber.QUEEN);
        Dealer dealer = new Dealer();
        dealer.addCard(card1);
        dealer.addCard(card2);

        assertThat(dealer.canReceiveAdditionalCards()).isFalse();
    }

    @Test
    void canReceiveAdditionalCards() {
    }
}
