package blackjack.domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MoneyTest {

    @Test
    @DisplayName("배팅 전 돈은 0원이다")
    void initial_money() {
        assertThat(Money.zero().getAmount()).isZero();
    }
}