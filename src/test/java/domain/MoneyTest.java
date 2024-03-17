package domain;

import domain.money.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;


class MoneyTest {
    @ParameterizedTest
    @DisplayName("양의 정수만 입력 가능하다")
    @CsvSource(value = {"0", "-1"})
    void validatePositiveTest(String money) {
        assertThatThrownBy(() -> new Money(money)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("정수 형태만 입력 가능하다.")
    @CsvSource(value = {"abc", "5.0"})
    void validateNumberTypeTest(String money) {
        assertThatThrownBy(() -> new Money(money)).isInstanceOf(NumberFormatException.class);
    }
}
