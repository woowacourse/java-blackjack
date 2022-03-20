package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingMoneyTest {

    @DisplayName("문자열 정수가 주어지면 생성된다.")
    @Test
    void 머니_생성() {
        assertDoesNotThrow(() -> BettingMoney.of("20000"));
    }

    @DisplayName("1000 미만의 정수가 들어오면 예외를 던진다.")
    @Test
    void 머니_금액_부족() {
        assertThatThrownBy(() -> BettingMoney.of("999"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("배팅 금액은 1000원 이상입니다.");
    }

    @DisplayName("1000으로 나누어 떨어지지 않는 경우 예외를 던진다.")
    @Test
    void 머니_단위() {
        assertThatThrownBy(() -> BettingMoney.of("1999"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("배팅 금액은 1000으로 나누어 떨어져야 합니다.");
    }

    @DisplayName("두 Money를 곱할 수 있다.")
    @Test
    void 머니_곱하기() {
        BettingMoney bettingMoney = BettingMoney.of("10000");

        BettingMoney result = bettingMoney.times(1.5);

        assertThat(result.getAmount()).isEqualTo(15000);
    }

    @DisplayName("두 Money에 음수를 곱할 수 있다.")
    @Test
    void 머니_음수() {
        BettingMoney bettingMoney = BettingMoney.of("10000");

        BettingMoney result = bettingMoney.times(-1);

        assertThat(result.getAmount()).isEqualTo(-10000);
    }
}