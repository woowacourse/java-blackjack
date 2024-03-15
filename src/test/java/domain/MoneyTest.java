package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class MoneyTest {

    @DisplayName("베팅 금액이 5000원 미만, 500000원 초과면 예외를 던진다.")
    @ParameterizedTest
    @ValueSource(strings = {"4999", "500001"})
    void invalidMoneyRangeTest(String money) {
        assertThatThrownBy(() -> new Money(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("베팅 금액은 5000원 이상, 500000원 이하만 가능합니다.");
    }
}
