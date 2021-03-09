package blackjack.domain;


import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingMoneyTest {
    protected static final int BETTING_BOUND = 100_000_000;

    @DisplayName("배팅 금액이 1억 이하이면, 배팅 가능")
    @Test
    void validBetting() {
        assertThatCode(() -> new BettingMoney(BETTING_BOUND))
            .doesNotThrowAnyException();
    }

    @DisplayName("배팅 금액이 1억 초과이면, 예외 발생")
    @Test
    void overBettingBoundException() {
        assertThatThrownBy(() -> new BettingMoney(BETTING_BOUND + 1))
            .isInstanceOf(IllegalArgumentException.class);
    }
}