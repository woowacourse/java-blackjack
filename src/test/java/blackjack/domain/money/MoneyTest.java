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

    @DisplayName("금액은 서로 더할 수 있다")
    @ParameterizedTest
    @CsvSource({"1500, 1500, 3000", "1234, 5555, 6789", "10, 1, 11"})
    void addTest(int value1, int value2, int expected) {
        Money money1 = new Money(value1);
        Money money2 = new Money(value2);

        Money actual = money1.add(money2);

        assertThat(actual.toInt()).isEqualTo(expected);
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
