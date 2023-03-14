package blackjackgame.domain;

import static blackjackgame.domain.CardFixture.*;
import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjackgame.domain.player.Dealer;

class DealerTest {
    @DisplayName("딜러가 가진 카드들의 합이 16이하면, true를 반환한다.")
    @Test
    void Should_PickOneCard_When_ScoreUnder16() {
        Dealer dealer = Dealer.from(List.of(CLOVER_SEVEN, CLOVER_NINE));

        assertThat(dealer.canHit()).isEqualTo(true);
    }

    @DisplayName("딜러가 가진 카드들의 합이 17이상이면, true를 반환한다.")
    @Test
    void Should_PickOneCard_When_ScoreOver17() {
        Dealer dealer = Dealer.from(List.of(CLOVER_KING, CLOVER_SEVEN));

        assertThat(dealer.canHit()).isEqualTo(false);
    }
}
