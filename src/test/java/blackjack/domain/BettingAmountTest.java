package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BettingAmountTest {

    @ParameterizedTest
    @DisplayName("범위를 벗어난 값으로 초기화하면 예외를 던진다.")
    @ValueSource(ints = {0, 1_000_000_001})
    void validateBound(int value) {
        // when
        // then
        assertThatThrownBy(() -> new BettingAmount(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("베팅 금액은 1이상 10억 이하여야 합니다.");
    }
}