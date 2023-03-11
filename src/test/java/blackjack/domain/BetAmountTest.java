package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BetAmountTest {
    @DisplayName("금액을 받아 생성할 수 있다.")
    @Test
    void createByInteger() {
        //given
        int amount = 10000;
        //when
        final var betAmount = new BetAmount(amount);
        //then
        assertThat(betAmount).isInstanceOf(BetAmount.class);
    }
}