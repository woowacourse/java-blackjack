package blackjack.domain.betting;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.domain.participant.Player;
import blackjack.domain.result.WinningResult;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProfitCalculatorTest {

    @Test
    @DisplayName("블랙잭일 때 수익을 계산한다.")
    void calculatePlayerBlackjackProfit() {
        Map<Player, WinningResult> playerWinningResultMap = new LinkedHashMap<>();
        Player player = new Player("앤지");
        player.createBettingMoney(new BettingMoney(1000));
        playerWinningResultMap.putIfAbsent(player, WinningResult.BLACKJACK);
        ProfitCalculator profitCalculator = new ProfitCalculator(playerWinningResultMap);


        profitCalculator.calculate();

        assertThat(profitCalculator.getPlayerProfit().get(player)).isEqualTo(1500);

    }

    @Test
    @DisplayName("WIN일 때 수익을 계산한다.")
    void calculatePlayerWinProfit() {
        Map<Player, WinningResult> playerWinningResultMap = new LinkedHashMap<>();
        Player player = new Player("앤지");
        player.createBettingMoney(new BettingMoney(1000));
        playerWinningResultMap.putIfAbsent(player, WinningResult.WIN);
        ProfitCalculator profitCalculator = new ProfitCalculator(playerWinningResultMap);


        profitCalculator.calculate();

        assertThat(profitCalculator.getPlayerProfit().get(player)).isEqualTo(1000);

    }

    @Test
    @DisplayName("DRAW일 때 수익을 계산한다.")
    void calculatePlayerDrawProfit() {
        Map<Player, WinningResult> playerWinningResultMap = new LinkedHashMap<>();
        Player player = new Player("앤지");
        player.createBettingMoney(new BettingMoney(1000));
        playerWinningResultMap.putIfAbsent(player, WinningResult.DRAW);
        ProfitCalculator profitCalculator = new ProfitCalculator(playerWinningResultMap);


        profitCalculator.calculate();

        assertThat(profitCalculator.getPlayerProfit().get(player)).isEqualTo(0);

    }

    @Test
    @DisplayName("LOSE일 때 수익을 계산한다.")
    void calculatePlayerLoseProfit() {
        Map<Player, WinningResult> playerWinningResultMap = new LinkedHashMap<>();
        Player player = new Player("앤지");
        player.createBettingMoney(new BettingMoney(1000));
        playerWinningResultMap.putIfAbsent(player, WinningResult.LOSE);
        ProfitCalculator profitCalculator = new ProfitCalculator(playerWinningResultMap);


        profitCalculator.calculate();

        assertThat(profitCalculator.getPlayerProfit().get(player)).isEqualTo(-1000);

    }

}
