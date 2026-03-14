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
    void recodeProfit_savesWinProfit_whenPlayerIsWin() {
        Player player = new Player(new PlayerName("user1"), new Hand());
        ProfitTable profitTable = new ProfitTable();
        int betAmount = 10000;
        int winProfit = betAmount;

        profitTable.recodeProfit(player, betAmount);
        int profit = profitTable.findProfitByPlayerName("user1");

        assertThat(profit).isEqualTo(winProfit);
    }

    @Test
    @DisplayName("플레이어가 패배했을 때의 수익(베팅금의 부호를 마이너스)을 수익테이블에 저장할 수 있다.")
    void recodeProfit_savesDefeatProfit_whenPlayerIsDefeat() {
        Player player = new Player(new PlayerName("user1"), new Hand());
        ProfitTable profitTable = new ProfitTable();
        int betAmount = 10000;
        int defeatProfit = -betAmount;

        profitTable.recodeProfit(player, defeatProfit);
        int profit = profitTable.findProfitByPlayerName("user1");

        assertThat(profit).isEqualTo(defeatProfit);
    }

    @Test
    @DisplayName("플레이어가 무승부했을 때의 수익(0원)을 수익테이블에 저장할 수 있다.")
    void recodeProfit_savesDrawProfit_whenPlayerIsDraw() {
        Player player = new Player(new PlayerName("user1"), new Hand());
        ProfitTable profitTable = new ProfitTable();
        int betAmount = 10000;
        int drawProfit = betAmount - betAmount;

        profitTable.recodeProfit(player, drawProfit);
        int profit = profitTable.findProfitByPlayerName("user1");

        assertThat(profit).isEqualTo(drawProfit);
    }

    @Test
    @DisplayName("플레이어가 블랙잭했을 때의 수익(베팅금의 0.5배)을 수익테이블에 저장할 수 있다.")
    void recodeProfit_savesBlackJackProfit_whenPlayerIsBlackJack() {
        Player player = new Player(new PlayerName("user1"), new Hand());
        ProfitTable profitTable = new ProfitTable();
        int betAmount = 10000;
        int blackJackProfit = (int) (betAmount * 1.5) - betAmount;

        profitTable.recodeProfit(player, blackJackProfit);
        int profit = profitTable.findProfitByPlayerName("user1");

        assertThat(profit).isEqualTo(blackJackProfit);
    }

    @Test
    @DisplayName("딜러의 수익을 계산할 수 있다")
    void dealerCalculateProfit_returnsDealerProfit() {
        Player winPlayer = new Player(new PlayerName("winPlayer"), new Hand());
        Player defeatPlayer = new Player(new PlayerName("defeatPlayer"), new Hand());
        ProfitTable profitTable = new ProfitTable();
        int winProfit = 10000;
        int defeatProfit = -15000;
        int expectedProfit = 5000;
        profitTable.recodeProfit(winPlayer, winProfit);
        profitTable.recodeProfit(defeatPlayer, defeatProfit);

        int dealerProfit = profitTable.dealerCalculateProfit();

        assertThat(dealerProfit).isEqualTo(expectedProfit);
    }
}
