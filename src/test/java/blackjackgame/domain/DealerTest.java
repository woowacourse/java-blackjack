package blackjackgame.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class DealerTest {

    @DisplayName("딜러가 가진 카드들의 합이 16이하면, true를 반환한다.")
    @Test
    void Should_PickOneCard_When_ScoreUnder16() {
        Card spade5 = new Card(Symbol.SPADE, CardValue.FIVE);
        Card clover8 = new Card(Symbol.CLOVER, CardValue.EIGHT);
        Player dealer = new Dealer(List.of(spade5, clover8));

        assertThat(dealer.canHit()).isEqualTo(true);
    }

    @DisplayName("딜러가 가진 카드들의 합이 17이상이면, true를 반환한다.")
    @Test
    void Should_PickOneCard_When_ScoreOver17() {
        Card spadeJ = new Card(Symbol.SPADE, CardValue.JACK);
        Card cloverK = new Card(Symbol.CLOVER, CardValue.KING);
        Player dealer = new Dealer(List.of(spadeJ, cloverK));

        assertThat(dealer.canHit()).isEqualTo(false);
    }
}
