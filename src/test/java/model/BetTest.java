package model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BetTest {
    @ParameterizedTest
    @DisplayName("10,000원 단위가 아닌 금액은 예외가 발생한다.")
    @ValueSource(ints = {5000, 0, -10000})
    void testValidateAmount(int amount) {
        assertThatThrownBy(() -> new Bet(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 배팅은 10,000원 단위로만 가능합니다.");
    }
}
