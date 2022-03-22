package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BettingTest {

    @DisplayName("플레이어가 이겼을 때 수익금이 올바르게 반환되는지 확인한다.")
    @Test
    void win_profit() {
        Betting betAmount = new Betting();
        GameResult gameResult = new GameResult();
        Player player = new Player("pobi");

        betAmount.bet(player, BettingMoney.of("5000"));
        gameResult.putResult(player, Profit.WIN);
        BettingMoney money = betAmount.getProfit(gameResult, player);

        assertThat(money.getMoney()).isEqualTo(5000);
    }

    @DisplayName("딜러의 수익금이 올바르게 반환되는지 확인한다.")
    @Test
    void dealer_profit() {
        Betting betAmount = new Betting();
        GameResult gameResult = new GameResult();
        Player player = new Player("pobi");

        betAmount.bet(player, BettingMoney.of("5000"));
        gameResult.putResult(player, Profit.WIN);
        BettingMoney money = betAmount.getDealerProfit(gameResult);

        assertThat(money.getMoney()).isEqualTo(-5000);
    }
}

