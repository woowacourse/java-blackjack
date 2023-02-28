package blackjackgame.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @DisplayName("딜러가 가진 카드들의 합을 반환한다.")
    @Test
    void Should_ReturnScore_When_Request() {
        Card card1 = new Card(Symbol.SPADE, CardValue.FIVE);
        Card card2 = new Card(Symbol.CLOVER, CardValue.EIGHT);
        Dealer dealer = new Dealer(card1, card2);

        assertThat(dealer.getScore()).isEqualTo(13);
    }

    @DisplayName("딜러가 가진 카드들의 합이 16이하면, true를 반환한다.")
    @Test
    void Should_PickOneCard_When_ScoreUnder16() {
        Card card1 = new Card(Symbol.SPADE, CardValue.FIVE);
        Card card2 = new Card(Symbol.CLOVER, CardValue.EIGHT);
        Dealer dealer = new Dealer(card1, card2);

        assertThat(dealer.isPick()).isEqualTo(true);
    }

    @DisplayName("딜러가 가진 카드들의 합이 17이상이면, true를 반환한다.")
    @Test
    void Should_PickOneCard_When_ScoreOver17() {
        Card card1 = new Card(Symbol.SPADE, CardValue.JACK);
        Card card2 = new Card(Symbol.CLOVER, CardValue.KING);
        Dealer dealer = new Dealer(card1, card2);

        assertThat(dealer.isPick()).isEqualTo(false);
    }
}
