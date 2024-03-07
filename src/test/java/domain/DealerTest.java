package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.strategy.SettedDecksGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    //TODO : 생성 부분 리팩터링
    @DisplayName("딜러가 카드를 뽑아서 저장한다.")
    @Test
    void hitTest() {
        // given
        Card card = new Card(Symbol.HEART, Rank.NINE);

        Dealer dealer = new Dealer();

        // when
        dealer.hit(card);

        // then
        assertThat(dealer.getHand()).hasSize(1);
    }

    @DisplayName("딜러가 가진 카드의 점수를 알 수 있다.")
    @Test
    void calculateTotalScoreTest() {
        // given
        Card card1 = new Card(Symbol.HEART, Rank.NINE);
        Card card2 = new Card(Symbol.SPADE, Rank.QUEEN);

        Dealer dealer = new Dealer();

        dealer.hit(card1);
        dealer.hit(card2);
        int expectedScore = 19;

        // when
        int result = dealer.calculateTotalScore();

        // then
        assertThat(result).isEqualTo(expectedScore);
    }

    @DisplayName("딜러가 버스트인지 확인한다.")
    @Test
    void isBust() {
        // given
        Card card1 = new Card(Symbol.HEART, Rank.NINE);
        Card card2 = new Card(Symbol.SPADE, Rank.QUEEN);
        Card card3 = new Card(Symbol.SPADE, Rank.THREE);

        Dealer dealer = new Dealer();

        dealer.hit(card1);
        dealer.hit(card2);
        dealer.hit(card3);

        // when
        boolean bust = dealer.isBust();

        // then
        assertThat(bust).isTrue();
    }
}
