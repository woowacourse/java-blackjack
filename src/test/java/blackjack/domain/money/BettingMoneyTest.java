package blackjack.domain.money;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class BettingMoneyTest {

    @ParameterizedTest
    @ValueSource(strings = {"100^0", "*", "2300)"})
    @DisplayName("숫자가 아닌 경우")
    void createBettingMoney(String value) {
        assertThatThrownBy(() ->
                new BettingMoney(value)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("음수인 경우")
    void negativeMoney() {
        assertThatThrownBy(() ->
                new BettingMoney("-1000")
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
