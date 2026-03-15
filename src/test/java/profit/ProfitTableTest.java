package profit;

import domain.participant.Hand;
import domain.participant.Player;
import domain.participant.PlayerName;
import domain.profit.ProfitTable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProfitTableTest {

    @Test
    @DisplayName("플레이어가 승리했을 때의 수익(베팅금 그대로)을 수익테이블에 저장할 수 있다.")
    void recordProfit_savesWinProfit_whenPlayerIsWin() {
        Player player = new Player(new PlayerName("user1"), new Hand());
        ProfitTable profitTable = new ProfitTable();
        int betAmount = 10000;
        int winProfit = betAmount;

        profitTable.recordProfit(player, betAmount);
        int profit = profitTable.findProfitByPlayerName("user1");

        assertThat(profit).isEqualTo(winProfit);
    }

    @Test
    @DisplayName("플레이어가 패배했을 때의 수익(베팅금의 부호를 마이너스)을 수익테이블에 저장할 수 있다.")
    void recordProfit_savesDefeatProfit_whenPlayerIsDefeat() {
        Player player = new Player(new PlayerName("user1"), new Hand());
        ProfitTable profitTable = new ProfitTable();
        int betAmount = 10000;
        int defeatProfit = -betAmount;

        profitTable.recordProfit(player, defeatProfit);
        int profit = profitTable.findProfitByPlayerName("user1");

        assertThat(profit).isEqualTo(defeatProfit);
    }

    @Test
    @DisplayName("플레이어가 무승부했을 때의 수익(0원)을 수익테이블에 저장할 수 있다.")
    void recordProfit_savesDrawProfit_whenPlayerIsDraw() {
        Player player = new Player(new PlayerName("user1"), new Hand());
        ProfitTable profitTable = new ProfitTable();
        int betAmount = 10000;
        int drawProfit = betAmount - betAmount;

        profitTable.recordProfit(player, drawProfit);
        int profit = profitTable.findProfitByPlayerName("user1");

        assertThat(profit).isEqualTo(drawProfit);
    }

    @Test
    @DisplayName("플레이어가 블랙잭했을 때의 수익(베팅금의 1.5배)을 수익테이블에 저장할 수 있다.")
    void recordProfit_savesBlackJackProfit_whenPlayerIsBlackJack() {
        Player player = new Player(new PlayerName("user1"), new Hand());
        ProfitTable profitTable = new ProfitTable();
        int betAmount = 10000;
        int blackJackProfit = (int) (betAmount * 1.5);

        profitTable.recordProfit(player, blackJackProfit);
        int profit = profitTable.findProfitByPlayerName("user1");

        assertThat(profit).isEqualTo(blackJackProfit);
    }

    @Test
    @DisplayName("플레이어가 승리했을 때 딜러의 수익을 계산할 수 있다.")
    void dealerCalculateProfit_returnsDealerProfit_WhenPlayerIsWin() {
        Player winPlayer = new Player(new PlayerName("winPlayer"), new Hand());
        ProfitTable profitTable = new ProfitTable();
        int winProfit = 10000;
        int expectedProfit = -10000;
        profitTable.recordProfit(winPlayer, winProfit);

        int dealerProfit = profitTable.dealerCalculateProfit();

        assertThat(dealerProfit).isEqualTo(expectedProfit);
    }

    @Test
    @DisplayName("플레이어가 패배했을 때 딜러의 수익을 계산할 수 있다.")
    void dealerCalculateProfit_returnsDealerProfit_WhenPlayerIsDefeat() {
        Player defeatPlayer = new Player(new PlayerName("defeatPlayer"), new Hand());
        ProfitTable profitTable = new ProfitTable();
        int defeatProfit = -15000;
        int expectedProfit = 15000;
        profitTable.recordProfit(defeatPlayer, defeatProfit);

        int dealerProfit = profitTable.dealerCalculateProfit();

        assertThat(dealerProfit).isEqualTo(expectedProfit);
    }

    @Test
    @DisplayName("플레이어와 딜러가 무승부했을 때 딜러의 수익을 계산할 수 있다.")
    void dealerCalculateProfit_returnsDealerProfit() {
        Player drawPlayer = new Player(new PlayerName("drawPlayer"), new Hand());
        ProfitTable profitTable = new ProfitTable();
        int drawProfit = 0;
        int expectedProfit = 0;
        profitTable.recordProfit(drawPlayer, drawProfit);

        int dealerProfit = profitTable.dealerCalculateProfit();

        assertThat(dealerProfit).isEqualTo(expectedProfit);
    }
}
