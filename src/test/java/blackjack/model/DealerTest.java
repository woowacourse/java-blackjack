package blackjack.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {
    @Test
    @DisplayName("딜러의 점수가 16점 이하면 반드시 카드 한장을 받아온다.")
    void canReceiveCard() {
        // given
        Dealer dealer = new Dealer();
        Card cardTen = new Card(Figure.SPADE, Number.TEN);
        Card cardSix = new Card(Figure.SPADE, Number.SIX);
        // when
        dealer.receiveCard(cardTen);
        dealer.receiveCard(cardSix);
        dealer.getScore();
        // then
        assertThat(dealer.canReceive()).isTrue();
    }

    @Test
    @DisplayName("딜러의 점수가 17점 이상이면 추가로 받아올 수 없다.")
    void cantReceiveCard() {
        // given
        Dealer dealer = new Dealer();
        Card cardTen = new Card(Figure.SPADE, Number.TEN);
        Card cardSeven = new Card(Figure.SPADE, Number.SEVEN);
        // when
        dealer.receiveCard(cardTen);
        dealer.receiveCard(cardSeven);
        dealer.getScore();
        // then
        assertThat(dealer.canReceive()).isFalse();
    }
}