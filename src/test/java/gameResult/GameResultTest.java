package gameResult;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameResultTest {

    @DisplayName("플레이어가 버스트일 경우 베팅한 금액을 잃는다.")
    @Test
    void playerBustMoneyEarnPercentage() {
        PlayerBust playerBust = new PlayerBust();

        Assertions.assertThat(playerBust.returnRate()).isEqualTo(-1);
    }

    @DisplayName("플레이어가 블랙잭일 경우 베팅한 금액의 1.5만큼 받는다.")
    @Test
    void playerBlackJackMoneyEarnPercentage() {
        PlayerBlackJack playerBlackJack = new PlayerBlackJack();

        Assertions.assertThat(playerBlackJack.returnRate()).isEqualTo(1.5);
    }

    @DisplayName("플레이어가 무승부일 경우 수익이 존재하지 않는다.")
    @Test
    void playerPushMoneyEarnPercentage() {
        PlayerPush playerPush = new PlayerPush();

        Assertions.assertThat(playerPush.returnRate()).isEqualTo(0);
    }

    @DisplayName("플레이어가 이길 경우 베팅한 금액만큼 받는다.")
    @Test
    void playerWinMoneyEarnPercentage() {
        PlayerWin playerWin = new PlayerWin();

        Assertions.assertThat(playerWin.returnRate()).isEqualTo(1);
    }

    @DisplayName("플레이어가 질 경우 베팅한 금액만큼 잃는다.")
    @Test
    void playerLoseMoneyEarnPercentage() {
        PlayerLose playerLose = new PlayerLose();

        Assertions.assertThat(playerLose.returnRate()).isEqualTo(-1);
    }
}
