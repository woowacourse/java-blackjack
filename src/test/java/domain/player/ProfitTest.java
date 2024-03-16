package domain.player;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ProfitTest {

    @ParameterizedTest
    @CsvSource(value = {"1.0,1,1.0", "1.5,1,1.5"})
    @DisplayName("게임을 이겼을 때 수익은 수익률과 베팅한 금액의 곱이다")
    void win(final double earningRate, final int betAmount, final double expected) {
        final Profit profit = new Profit(earningRate, betAmount);
        Assertions.assertThat(profit.win()).isEqualTo(expected);
    }

    @Test
    void lose() {
    }

    @Test
    void tie() {
    }
}
