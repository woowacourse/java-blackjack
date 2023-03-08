package blackjack.domain.betting;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ProfitTest {

    @ParameterizedTest(name = "원금: {0}, 결과: {1}")
    @CsvSource(value = {"10000,15000", "13333,19999"})
    void 수익이_50_퍼센트_증가한다(final int value, final int expected) {
        final Profit profit = new Profit(value);

        final Profit increaseProfit = profit.increaseFiftyPercent();

        Assertions.assertThat(increaseProfit.getValue()).isEqualTo(expected);
    }
}

class Profit {

    private final int value;

    public Profit(final int value) {
        this.value = value;
    }

    public Profit increaseFiftyPercent() {
        return new Profit(value + (int) (value * 0.5));
    }

    public int getValue() {
        return value;
    }
}
