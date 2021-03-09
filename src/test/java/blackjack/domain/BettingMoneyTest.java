package blackjack.domain;


import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingMoneyTest {
    private static final int MIN_BETTING_MONEY_BOUND = 1_000;
    protected static final int MAX_BETTING_MONEY_BOUND = 100_000_000;

    @DisplayName("배팅 금액이 1000원 이상, 1억원 이하이면, 배팅 가능")
    @Test
    void validBetting() {
        assertThatCode(() -> new BettingMoney(MAX_BETTING_MONEY_BOUND))
            .doesNotThrowAnyException();
        assertThatCode(() -> new BettingMoney(MIN_BETTING_MONEY_BOUND))
            .doesNotThrowAnyException();
    }

    @DisplayName("배팅 금액이 1000원 미만이면, 예외 발생")
    @Test
    void underBettingBoundException() {
        assertThatThrownBy(() -> new BettingMoney(MIN_BETTING_MONEY_BOUND - 1))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("배팅 금액이 1억원 초과이면, 예외 발생")
    @Test
    void overBettingBoundException() {
        assertThatThrownBy(() -> new BettingMoney(MAX_BETTING_MONEY_BOUND + 1))
            .isInstanceOf(IllegalArgumentException.class);
    }
}