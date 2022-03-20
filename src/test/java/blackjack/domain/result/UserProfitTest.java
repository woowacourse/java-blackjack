package blackjack.domain.result;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserProfitTest {

    @Test
    @DisplayName("승리 시 베팅 금액과 동일한 금액만큼 이익이 발생한다.")
    public void calculateProfitWinTest() {
        assertThat(UserProfit.calculateMoney(UserResult.WIN, false, 5000))
                .isEqualTo(5000);
    }

    @Test
    @DisplayName("블랙잭 승리 시 베팅 금액의 1.5배만큼의 이익이 발생한다.")
    public void calculateProfitWinWithBlackJackTest() {
        assertThat(UserProfit.calculateMoney(UserResult.WIN, true, 5000))
                .isEqualTo(7500);
    }

    @Test
    @DisplayName("패배 시 베팅 금액만큼 손해가 발생한다.")
    public void calculateProfitLoseTest() {
        assertThat(UserProfit.calculateMoney(UserResult.LOSE, false, 5000))
                .isEqualTo(-5000);
    }

    @Test
    @DisplayName("무승부 시 이득이 발생하지 않는다.")
    public void calculateProfitDrawTest() {
        assertThat(UserProfit.calculateMoney(UserResult.DRAW, false, 5000))
                .isEqualTo(0);
    }
}
