package domain.player;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ProfitTest {

    @ParameterizedTest
    @CsvSource(value = {"1.0, 1, 1.0", "1.5, 1, 1.5"})
    @DisplayName("게임을 이겼을 때 수익은 수익률과 베팅한 금액의 곱이다")
    void win(final double earningRate, final int betAmount, final double expected) {
        final Profit profit = new Profit(earningRate, betAmount);

        Assertions.assertThat(profit.win()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"1.0, 1, -1.0", "1.5, 1,  -1.5"})
    @DisplayName("게임에서 졌을 때는 걸었던 돈을 모두 잃는다")
    void lose(final double earningRate, final int betAmount, final double expected) {
        final Profit profit = new Profit(earningRate, betAmount);

        Assertions.assertThat(profit.lose()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"1.0, 1, 0", "1.5, 1,  0"})
    @DisplayName("게임에서 비겼을 때 얻을 수 있는 수익은 없다")
    void tie(final double earningRate, final int betAmount, final double expected) {
        final Profit profit = new Profit(earningRate, betAmount);

        Assertions.assertThat(profit.tie()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"1,-1", "-1,1","0,0"})
    @DisplayName("값이 주어졌을 때 부호를 바꾸어 반환한다")
    void reverse(final int actual, final int expected) {
        Assertions.assertThat(Profit.reverse(actual)).isEqualTo(expected);
    }
}
