package blackjack.domain.user;

import blackjack.domain.result.Exception.BettingMoneyException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BettingMoneyTest {

    @Test
    void from_ValidValue_ShouldCreateBettingMoney() {
        assertThat(BettingMoney.from("30000")).isNotNull();
    }

    @Test
    void from_NotNumberValue_ShouldThrowException() {
        assertThatThrownBy(() -> {
            BettingMoney.from("three");
        }).isInstanceOf(BettingMoneyException.class)
                .hasMessageContaining("배팅 금액")
                .hasMessageContaining("숫자");
    }

    @Test
    void from_OutOfRangeValue_ShouldThrowException() {
        assertThatThrownBy(() -> {
            BettingMoney.from("0");
        }).isInstanceOf(BettingMoneyException.class)
                .hasMessageContaining("배팅 금액")
                .hasMessageContaining("양수");
    }
}