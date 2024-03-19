package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingAmountTest {
    @Test
    @DisplayName("양의 정수가 아니면 예외로 처리한다")
    void notPositiveInteger() {
        Assertions.assertThatThrownBy(() -> new BettingAmount(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("베팅 금액은 양의 정수여야 합니다");
    }
}
