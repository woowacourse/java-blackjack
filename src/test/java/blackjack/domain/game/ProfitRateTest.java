package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ProfitRateTest {

    private final Dealer dealer = new Dealer();
    private final Player player = new Player("pobi");
    private final Card aceSpade = Card.of(Denomination.ACE, Suit.SPADE);
    private final Card tenSpade = Card.of(Denomination.KING, Suit.SPADE);

    @DisplayName("딜러와 플레이어 모두 블랙잭일 경우 수익률이 0인 것을 확인한다.")
    @Test
    void rate_blackjack_tie() {
        dealer.deal(List.of(aceSpade, tenSpade));
        player.deal(List.of(aceSpade, tenSpade));

        assertThat(ProfitRate.rateBlackjack(dealer, player)).isEqualTo(0);
    }

    @DisplayName("딜러만 블랙잭인 경우 수익률이 -1인 것을 확인한다.")
    @Test
    void rate_blackjack_lost() {
        dealer.deal(List.of(aceSpade, tenSpade));
        player.deal(List.of(tenSpade, tenSpade));

        assertThat(ProfitRate.rateBlackjack(dealer, player)).isEqualTo(-1);
    }

    @DisplayName("플레이어만 블랙잭인 경우 수익률이 1.5인 것을 확인한다.")
    @Test
    void rate_blackjack_win() {
        dealer.deal(List.of(tenSpade, tenSpade));
        player.deal(List.of(aceSpade, tenSpade));

        assertThat(ProfitRate.rateBlackjack(dealer, player)).isEqualTo(1.5);
    }

    @DisplayName("플레이어가 버스트일 경우 수익률이 -1인 것을 확인한다.")
    @Test
    void rate_stay_player_bust() {
        dealer.deal(List.of(tenSpade, tenSpade));
        player.deal(List.of(tenSpade, tenSpade, tenSpade));

        assertThat(ProfitRate.rateStay(dealer, player)).isEqualTo(-1);
    }

    @DisplayName("딜러가 버스트일 경우 수익률이 1인 것을 확인한다.")
    @Test
    void rate_stay_dealer_bust() {
        dealer.deal(List.of(tenSpade, tenSpade, tenSpade));
        player.deal(List.of(tenSpade, tenSpade));

        assertThat(ProfitRate.rateStay(dealer, player)).isEqualTo(1);
    }

    @DisplayName("모두 버스트가 아니며 동점일 경우 수익률이 0인 것을 확인한다.")
    @Test
    void rate_stay_tie() {
        dealer.deal(List.of(tenSpade, tenSpade));
        player.deal(List.of(tenSpade, tenSpade));

        assertThat(ProfitRate.rateStay(dealer, player)).isEqualTo(0);
    }

    @DisplayName("모두 버스트가 아니며 플레이어의 점수가 더 높을 경우 수익률이 1인 것을 확인한다.")
    @Test
    void rate_stay_win() {
        dealer.deal(List.of(tenSpade));
        player.deal(List.of(tenSpade, tenSpade));

        assertThat(ProfitRate.rateStay(dealer, player)).isEqualTo(1);
    }

    @DisplayName("모두 버스트가 아니며 플레이어의 점수가 더 낮을 경우 수익률이 -1인 것을 확인한다.")
    @Test
    void rate_stay_lose() {
        dealer.deal(List.of(tenSpade, tenSpade));
        player.deal(List.of(tenSpade));

        assertThat(ProfitRate.rateStay(dealer, player)).isEqualTo(-1);
    }
}