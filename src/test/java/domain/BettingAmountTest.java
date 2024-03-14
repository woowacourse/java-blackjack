package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BettingAmountTest {
    @ParameterizedTest
    @DisplayName("양의 정수가 아니면 예외로 처리한다")
    @ValueSource(strings = {"-1", "0", "test"})
    void notPositiveInteger(String input) {
        Assertions.assertThatThrownBy(() -> new BettingAmount(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("베팅 금액은 양의 정수여야 합니다");
    }
}
