package blackjack.domain.result;

import blackjack.domain.game.Hand;
import blackjack.domain.game.Player;
import blackjack.domain.profit.PlayerProfit;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class PlayerProfitTest {
    @Test
    void 같은_플레이어에_대한_결과인_경우_true를_반환한다() {
        // given
        Player player = new Player("히로", new Hand(), new BetAmount(1_000));
        PlayerProfit playerProfit = new PlayerProfit(player, 1_000);

        // when
        boolean actual = playerProfit.isResultOf(player);

        // then
        assertTrue(actual);
    }
}
