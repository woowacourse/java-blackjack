package blackjack.domain.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static blackjack.fixture.Fixture.ACE;
import static blackjack.fixture.Fixture.TEN;
import static org.assertj.core.api.Assertions.assertThat;

class EarningRateTest {

    private final Dealer dealer = new Dealer();
    private final Player player = new Player("pobi");

    @DisplayName("딜러와 플레이어 모두 블랙잭일 경우 수익률이 0인 것을 확인한다.")
    @Test
    void rate_blackjack_tie() {
        dealer.deal(List.of(ACE, TEN));
        player.deal(List.of(ACE, TEN));

        assertThat(EarningRate.rateBlackjack(dealer, player)).isEqualTo(0);
    }

    @DisplayName("딜러만 블랙잭인 경우 수익률이 -1인 것을 확인한다.")
    @Test
    void rate_blackjack_lost() {
        dealer.deal(List.of(ACE, TEN));
        player.deal(List.of(TEN, TEN));

        assertThat(EarningRate.rateBlackjack(dealer, player)).isEqualTo(-1);
    }

    @DisplayName("플레이어만 블랙잭인 경우 수익률이 1.5인 것을 확인한다.")
    @Test
    void rate_blackjack_win() {
        dealer.deal(List.of(TEN, TEN));
        player.deal(List.of(ACE, TEN));

        assertThat(EarningRate.rateBlackjack(dealer, player)).isEqualTo(1.5);
    }

    @DisplayName("플레이어가 버스트일 경우 수익률이 -1인 것을 확인한다.")
    @Test
    void rate_stay_player_bust() {
        dealer.deal(List.of(TEN, TEN));
        player.deal(List.of(TEN, TEN, TEN));

        assertThat(EarningRate.rateStay(dealer, player)).isEqualTo(-1);
    }

    @DisplayName("딜러가 버스트일 경우 수익률이 1인 것을 확인한다.")
    @Test
    void rate_stay_dealer_bust() {
        dealer.deal(List.of(TEN, TEN, TEN));
        player.deal(List.of(TEN, TEN));

        assertThat(EarningRate.rateStay(dealer, player)).isEqualTo(1);
    }

    @DisplayName("모두 버스트가 아니며 동점일 경우 수익률이 0인 것을 확인한다.")
    @Test
    void rate_stay_tie() {
        dealer.deal(List.of(TEN, TEN));
        player.deal(List.of(TEN, TEN));

        assertThat(EarningRate.rateStay(dealer, player)).isEqualTo(0);
    }

    @DisplayName("모두 버스트가 아니며 플레이어의 점수가 더 높을 경우 수익률이 1인 것을 확인한다.")
    @Test
    void rate_stay_win() {
        dealer.deal(List.of(TEN));
        player.deal(List.of(TEN, TEN));

        assertThat(EarningRate.rateStay(dealer, player)).isEqualTo(1);
    }

    @DisplayName("모두 버스트가 아니며 플레이어의 점수가 더 낮을 경우 수익률이 -1인 것을 확인한다.")
    @Test
    void rate_stay_lose() {
        dealer.deal(List.of(TEN, TEN));
        player.deal(List.of(TEN));

        assertThat(EarningRate.rateStay(dealer, player)).isEqualTo(-1);
    }
}