package domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class BetAmountTest {
    @ParameterizedTest(name = "배팅 금액은 최소 10원, 최대 1억이다.")
    @ValueSource(ints = {9, 100000001})
    void createBetAmountFailTest(int money) {
        assertThatThrownBy(() -> new BetAmount(money))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest(name = "배팅 금액은 최소 10원, 최대 1억이다.")
    @ValueSource(ints = {10, 100000000})
    void createBetAmountSuccessTest(int money) {
        assertDoesNotThrow(() -> new BetAmount(money));
    }
}
