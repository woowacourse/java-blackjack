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

    @Test
    @DisplayName("21이 넘으면 버스트 처리한다.")
    void isBustTest() {
        // given
        Participant participant = new Dealer();

        participant.receiveCard(new Card(Figure.DIAMOND, Number.TWO));
        participant.receiveCard(new Card(Figure.DIAMOND, Number.TEN));
        participant.receiveCard(new Card(Figure.SPADE, Number.TEN));
        // when & then
        assertThat(participant.isBust()).isTrue();
    }

    @Test
    @DisplayName("21이 넘지 않으면 버스트 처리하지 않는다.")
    void isBustTest2() {
        // given
        Participant participant = new Dealer();

        participant.receiveCard(new Card(Figure.DIAMOND, Number.TEN));
        participant.receiveCard(new Card(Figure.SPADE, Number.ACE));

        // when & then
        assertThat(participant.isBust()).isFalse();
    }
}
