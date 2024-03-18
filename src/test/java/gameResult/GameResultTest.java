package gameResult;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import participant.player.BetMoney;
import resultJudge.GameResult;

class GameResultTest {

    private BetMoney betMoney;

    @BeforeEach
    void setUp() {
        betMoney = new BetMoney(3000);
    }

    @DisplayName("플레이어가 버스트일 경우 베팅한 금액을 잃는다.")
    @Test
    void playerBustMoneyEarnPercentage() {
        double moneyReturnPercent = GameResult.PLAYER_BUST.getMoneyReturnPercent();

        Assertions.assertThat(betMoney.betMoneyResult(moneyReturnPercent)).isEqualTo(-3000);
    }

    @DisplayName("플레이어가 블랙잭일 경우 베팅한 금액의 1.5만큼 받는다.")
    @Test
    void playerBlackJackMoneyEarnPercentage() {
        double moneyReturnPercent = GameResult.PLAYER_BLACKJACK.getMoneyReturnPercent();

        Assertions.assertThat(betMoney.betMoneyResult(moneyReturnPercent)).isEqualTo(4500);
    }

    @DisplayName("플레이어가 무승부일 경우 수익이 존재하지 않는다.")
    @Test
    void playerPushMoneyEarnPercentage() {
        double moneyReturnPercent = GameResult.PLAYER_PUSH.getMoneyReturnPercent();

        Assertions.assertThat(betMoney.betMoneyResult(moneyReturnPercent)).isEqualTo(0);
    }

    @DisplayName("플레이어가 이길 경우 베팅한 금액만큼 받는다.")
    @Test
    void playerWinMoneyEarnPercentage() {
        double moneyReturnPercent = GameResult.PLAYER_WIN.getMoneyReturnPercent();

        Assertions.assertThat(betMoney.betMoneyResult(moneyReturnPercent)).isEqualTo(3000);
    }

    @DisplayName("플레이어가 질 경우 베팅한 금액만큼 잃는다.")
    @Test
    void playerLoseMoneyEarnPercentage() {
        double moneyReturnPercent = GameResult.PLAYER_LOSE.getMoneyReturnPercent();

        Assertions.assertThat(betMoney.betMoneyResult(moneyReturnPercent)).isEqualTo(-3000);
    }
}
