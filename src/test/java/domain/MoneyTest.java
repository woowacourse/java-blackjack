package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;


class MoneyTest {
    @ParameterizedTest
    @DisplayName("양의 정수만 입력 가능하다")
    @CsvSource(value = {"0", "-1", "abc"})
    void validateMoneyTest(String money) {
        assertThatThrownBy(() -> new Money(money)).isInstanceOf(NumberFormatException.class);
    }
}
