package blackjack.domain.profit;

import blackjack.domain.game.Hand;
import blackjack.domain.game.Player;
import blackjack.domain.result.BetAmount;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.Test;

class DealerProfitsTest {
    @Test
    void 이미_저장된_플레이어에_대한_딜러의_수익_결과인_경우_예외를_던진다() {
        // given
        Player player = new Player("히로", new Hand(), new BetAmount(1_000));

        DealerProfits dealerProfits = new DealerProfits();
        dealerProfits.add(player, new DealerProfit(0));

        // when & then
        assertThatThrownBy(() -> dealerProfits.add(player, new DealerProfit(1_000)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 플레이어에_대한_새로운_딜러의_수익_결과가_저장되는_경우_예외를_던지지_않는다() {
        // given
        Player player = new Player("히로", new Hand(), new BetAmount(1_000));

        DealerProfits dealerProfits = new DealerProfits();

        // when & then
        assertThatCode(() -> dealerProfits.add(player, new DealerProfit(1_000)))
                .doesNotThrowAnyException();
    }

    @Test
    void 플레이어에_대한_수익_결과를_찾는다() {
        // given
        Player player = new Player("히로", new Hand(), new BetAmount(1_000));

        DealerProfits dealerProfits = new DealerProfits();
        DealerProfit dealerProfit = new DealerProfit(0);
        dealerProfits.add(player, dealerProfit);

        // when & then
        assertThat(dealerProfits.findByPlayer(player)).isEqualTo(dealerProfit);
    }
}
