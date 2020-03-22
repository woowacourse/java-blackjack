package blackjack.domain.generic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BettingMoneyTest {

    @DisplayName("0보다 작은 금액을 베팅하려하는 경우 Exception")
    @Test
    void ofWhenNegativeAndZero() {
        assertThatThrownBy(() -> BettingMoney.of(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("베팅 금액은 0원 이상이어야 합니다.");
    }

}