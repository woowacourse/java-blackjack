package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BattingMoneyTest {

    @DisplayName("문자열 정수가 주어지면 생성된다.")
    @Test
    void 머니_생성() {
        assertDoesNotThrow(() -> new BattingMoney("20000"));
    }

    @DisplayName("두 Money가 주어지면 더할 수 있다.")
    @Test
    void 머니_더하기() {
        BattingMoney bettingMoney1 = new BattingMoney("10000");
        BattingMoney bettingMoney2 = new BattingMoney("20000");

        BattingMoney result = bettingMoney1.add(bettingMoney2);

        assertThat(result).isEqualTo(new BattingMoney("30000"));
    }
    
    @DisplayName("두 Money를 곱할 수 있다.")
    @Test
    void 머니_곱하기() {
        BattingMoney bettingMoney = new BattingMoney("10000");

        BattingMoney result = bettingMoney.times(1.5);

        assertThat(result.getAmount()).isEqualTo(15000);
    }

    @DisplayName("두 Money에 음수를 곱할 수 있다.")
    @Test
    void 머니_음수() {
        BattingMoney bettingMoney = new BattingMoney("10000");

        BattingMoney result = bettingMoney.times(-1);

        assertThat(result.getAmount()).isEqualTo(-10000);
    }
}