package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingMoneyTest {

    @DisplayName("문자열 정수가 주어지면 생성된다.")
    @Test
    void 머니_생성() {
        assertDoesNotThrow(() -> new BettingMoney(20000));
    }

    @DisplayName("두 Money를 곱할 수 있다.")
    @Test
    void 머니_곱하기() {
        BettingMoney bettingMoney = new BettingMoney(10000);

        BettingMoney result = bettingMoney.times(1.5);

        assertThat(result.getAmount()).isEqualTo(15000);
    }

    @DisplayName("두 Money에 음수를 곱할 수 있다.")
    @Test
    void 머니_음수() {
        BettingMoney bettingMoney = new BettingMoney(10000);

        BettingMoney result = bettingMoney.times(-1);

        assertThat(result.getAmount()).isEqualTo(-10000);
    }
}