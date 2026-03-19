package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class MoneyTest {

    @DisplayName("Money 객체를 생성한다.")
    @ParameterizedTest
    @ValueSource(strings = {"10", "1000", "2147483640"})
    void createMoney(String amount) {
        Money money = new Money(amount);
        assertThat(money.getAmount()).isEqualTo(Integer.parseInt(amount));
    }

    @DisplayName("베팅 금액이 int 최대값을 초과하면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"2147483648", "3000000000", "10000000000"})
    void throwExceptionWhenOutOfRange(String invalidInput) {
        assertThatThrownBy(() -> new Money(invalidInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("베팅 금액은 2,147,483,647원 이하로만 입력해주세요.");
    }

    @DisplayName("베팅 금액이 1원 미만이면 예외가 발생한다.")
    @Test
    void throwExceptionWhenBelowMinimum() {
        assertThatThrownBy(() -> new Money("0"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("베팅 금액은 1원 이상만 입력해주세요.");
    }

    @DisplayName("베팅 금액이 10원 단위가 아니면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"1", "15", "1009"})
    void throwExceptionWhenInvalidUnit(String invalidInput) {
        assertThatThrownBy(() -> new Money(invalidInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("베팅 금액은 10원 단위여야 합니다.");
    }

    @DisplayName("베팅 금액에 배당률을 곱하여 새로운 Money 객체를 반환한다.")
    @ParameterizedTest
    @CsvSource({
            "10000, 1.5, 15000",
            "10000, -1.0, -10000",
            "10000, 0.0, 0"
    })
    void multiply(String initialAmount, double rate, int expectedAmount) {
        Money money = new Money(initialAmount);
        Money multiplied = money.multiply(rate);
        assertThat(multiplied.getAmount()).isEqualTo(expectedAmount);
    }

    @DisplayName("다른 금액을 차감하여 새로운 Money 객체를 반환한다.")
    @Test
    void subtract() {
        Money money = new Money("10000");
        Money target = new Money("3000");
        Money result = money.subtract(target);
        assertThat(result.getAmount()).isEqualTo(7000);
    }
}
