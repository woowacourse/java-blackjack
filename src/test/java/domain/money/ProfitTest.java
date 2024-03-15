package domain.money;

import static domain.money.GameResult.DRAW;
import static domain.money.GameResult.LOSE;
import static domain.money.GameResult.WIN;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProfitTest {
    @Test
    @DisplayName("승리 시, 베팅 금액만큼 금액을 얻는다.")
    void betAmountTimesTest() {
        Profit profit = new Profit(10000);

        Profit newAmount = profit.change(WIN);

        assertThat(newAmount.value()).isEqualTo(10000);
    }

    @Test
    @DisplayName("무승부 시, 0원이 된다.")
    void betAmountDrawTest() {
        Profit profit = new Profit(10000);

        Profit newAmount = profit.change(DRAW);

        assertThat(newAmount.value()).isEqualTo(0);
    }

    @Test
    @DisplayName("패배 시, 베팅 금액을 잃는다.")
    void betAmountLoseTest() {
        Profit profit = new Profit(10000);

        Profit newAmount = profit.change(LOSE);

        assertThat(newAmount.value()).isEqualTo(-10000);
    }

    @Test
    @DisplayName("블랙잭 배수만큼 곱한다.")
    void changeByBlackJackTest() {
        Profit profit = new Profit(10000);

        Profit newAmount = profit.changeByBlackjack();

        assertThat(newAmount.value()).isEqualTo(15000);
    }
}
