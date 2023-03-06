package blackjackgame.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @DisplayName("딜러가 가진 카드들의 합이 16이하면, true를 반환한다.")
    @Test
    void Should_PickOneCard_When_ScoreUnder16() {
        Player dealer = new Dealer();
        Card spade5Card = new Card(Symbol.SPADE, CardValue.FIVE);
        Card clover8Card = new Card(Symbol.CLOVER, CardValue.EIGHT);
        dealer.addCard(spade5Card);
        dealer.addCard(clover8Card);

        assertThat(dealer.canHit()).isEqualTo(true);
    }

    @DisplayName("딜러가 가진 카드들의 합이 17이상이면, true를 반환한다.")
    @Test
    void Should_PickOneCard_When_ScoreOver17() {
        Player dealer = new Dealer();
        Card spadeJCard = new Card(Symbol.SPADE, CardValue.JACK);
        Card cloverKCard = new Card(Symbol.CLOVER, CardValue.KING);
        dealer.addCard(spadeJCard);
        dealer.addCard(cloverKCard);

        assertThat(dealer.canHit()).isEqualTo(false);
    }
}
