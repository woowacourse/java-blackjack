package profit;

import domain.bet.BetTable;
import domain.participant.Hand;
import domain.participant.Player;
import domain.participant.PlayerName;
import domain.profit.ProfitCalculator;
import domain.result.Result;
import domain.result.ResultInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProfitCalculatorTest {

    @Test
    @DisplayName("플레이어가 게임을 이겼을 때 수익(베팅한 금액만큼)을 계산한다.")
    void playerCalculateProfit_returnsBetProfit_whenPlayerIsWin() {
        Player player = new Player(new PlayerName("user1"), new Hand());
        BetTable betTable = new BetTable();
        int betAmount = 10000;
        betTable.recordAmount(player.getName(), betAmount);
        ProfitCalculator calculator = new ProfitCalculator(betTable);
        Result result = new Result();
        result.setPlayerResult(player.getName(), ResultInfo.WIN);
        int expectedProfit = 10000;

        int profit = calculator.playerCalculateProfit(result, player);

        assertThat(profit).isEqualTo(expectedProfit);
    }

    @Test
    @DisplayName("플레이어가 게임을 졌을 때 수익(베팅한 금액의 마이너스만큼)을 계산한다.")
    void playerCalculateProfit_returnsBetLoss_whenPlayerIsDefeat() {
        Player player = new Player(new PlayerName("user1"), new Hand());
        BetTable betTable = new BetTable();
        int betAmount = 10000;
        betTable.recordAmount(player.getName(), betAmount);
        ProfitCalculator calculator = new ProfitCalculator(betTable);
        Result result = new Result();
        result.setPlayerResult(player.getName(), ResultInfo.DEFEAT);
        int expectedProfit = -10000;

        int profit = calculator.playerCalculateProfit(result, player);

        assertThat(profit).isEqualTo(expectedProfit);
    }

    @Test
    @DisplayName("플레이어가 게임을 비겼을 때 수익(0원)을 계산한다.")
    void playerCalculateProfit_returnsZero_whenPlayerAndDealerIsDraw() {
        Player player = new Player(new PlayerName("user1"), new Hand());
        BetTable betTable = new BetTable();
        int betAmount = 10000;
        betTable.recordAmount(player.getName(), betAmount);
        ProfitCalculator calculator = new ProfitCalculator(betTable);
        Result result = new Result();
        result.setPlayerResult(player.getName(), ResultInfo.DRAW);
        int expectedProfit = 0;

        int profit = calculator.playerCalculateProfit(result, player);

        assertThat(profit).isEqualTo(expectedProfit);
    }

    @Test
    @DisplayName("플레이어가 블랙잭으로 이겼을 때 수익(베팅한 금액의 1.5배)을 계산한다.")
    void playerCalculateProfit_returnsOneAndHalfBet_whenPlayerIsBlackjack() {
        Player player = new Player(new PlayerName("user1"), new Hand());
        BetTable betTable = new BetTable();
        int betAmount = 10000;
        betTable.recordAmount(player.getName(), betAmount);
        ProfitCalculator calculator = new ProfitCalculator(betTable);
        Result result = new Result();
        result.setPlayerResult(player.getName(), ResultInfo.BLACKJACK_WIN);
        int expectedProfit = 15000;

        int profit = calculator.playerCalculateProfit(result, player);

        assertThat(profit).isEqualTo(expectedProfit);
    }
}
