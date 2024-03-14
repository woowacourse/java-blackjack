package blackjack.domain.money;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class BetAmountTest {

    @DisplayName("배팅 금액은 0 또는 음수가 될 수 없다")
    @ParameterizedTest
    @ValueSource(ints = {0, -1, -1000})
    void validateTest_whenValueIsNotPositive_throwException(int value) {

        assertThatThrownBy(() -> new BetAmount(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("배팅 금액은 양수이어야 합니다.");
    }

    @DisplayName("배팅 금액은 서로 더할 수 있다")
    @ParameterizedTest
    @CsvSource({"1500, 1500, 3000", "1234, 5555, 6789", "10, 1, 11"})
    void addTest(int value1, int value2, int expected) {
        BetAmount betAmount1 = new BetAmount(value1);
        BetAmount betAmount2 = new BetAmount(value2);

        BetAmount actual = betAmount1.add(betAmount2);

        assertThat(actual.toInt()).isEqualTo(expected);
    }

    @DisplayName("배팅 금액은 해당 배수만큼 곱할 수 있다")
    @ParameterizedTest
    @CsvSource({"1.5, 1500", "2, 2000", "2.25, 2250"})
    void multiplyTest(double multiplier, int expected) {
        BetAmount betAmount = new BetAmount(1000);

        BetAmount actual = betAmount.multiply(multiplier);

        assertThat(actual.toInt()).isEqualTo(expected);
    }
}
