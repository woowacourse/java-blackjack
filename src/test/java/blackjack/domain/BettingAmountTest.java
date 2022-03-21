package blackjack.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BettingAmountTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 10000})
    @DisplayName("배팅 금액은 0 이상의 수를 입력받는다.")
    void constructor(int money) {
        assertDoesNotThrow(() -> new BettingAmount(money));
    }

    @Test
    @DisplayName("배팅 금액은 음수이면 안된다.")
    void constructor_throwNegative() {
        Assertions.assertThatThrownBy(() -> new BettingAmount(-1000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("배팅 금액은 음수이면 안됩니다.");
    }
}
