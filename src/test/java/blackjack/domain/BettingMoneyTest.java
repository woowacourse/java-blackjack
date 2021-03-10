package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class BettingMoneyTest {

    @ParameterizedTest
    @ValueSource(strings = {"100^0", "*", "2300)", "1000.5"})
    @DisplayName("숫자가 아닌 경우")
    void createBettingMoney(String value) {
        assertThatThrownBy(() -> {
            BigDecimal bettingMoney;
            try {
                int money = Integer.parseInt(value);
                bettingMoney = new BigDecimal(money);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException();
            }
            new BettingMoney(bettingMoney);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("음수인 경우")
    void negativeMoney() {
        assertThatThrownBy(() ->
                new BettingMoney(new BigDecimal(-1000))
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
