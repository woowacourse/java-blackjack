package model.betting;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BetAmountTest {

    @DisplayName("배팅금이 0보다 큰 정수가 아니면 예외 발생")
    @ParameterizedTest
    @ValueSource(ints = {0, -1, -20})
    void testInvalidBetAmount(int money) {
        assertThatThrownBy(() -> new BetAmount(money))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
