package blackjack.domain.money;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class MoneyTest {

    @DisplayName("금액은 0 또는 음수가 될 수 없다")
    @ParameterizedTest
    @ValueSource(ints = {0, -1, -1000})
    void validateTest_whenValueIsNotPositive_throwException(int value) {

        assertThatThrownBy(() -> new Money(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("금액은 양수이어야 합니다.");
    }

    @DisplayName("금액은 해당 배수만큼 곱할 수 있다")
    @ParameterizedTest
    @CsvSource({"1.5, 1500", "2, 2000", "2.25, 2250"})
    void multiplyTest(double multiplier, int expected) {
        Money money = new Money(1000);

        Money actual = money.multiply(multiplier);

        assertThat(actual.toInt()).isEqualTo(expected);
    }

}
