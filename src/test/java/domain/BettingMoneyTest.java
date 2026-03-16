package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BettingMoneyTest {

    @Test
    void 배팅금액은_0보다_커야_한다() {
        assertThatThrownBy(() -> new BettingMoney(0))
                .isInstanceOf(IllegalArgumentException.class);
    }
}