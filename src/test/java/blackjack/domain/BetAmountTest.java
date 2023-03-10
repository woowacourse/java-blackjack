package blackjack.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class BetAmountTest {
    @Test
    void create() {
        assertDoesNotThrow(() -> new BetAmount(10000));
    }

    @Test
    void getValue() {
        BetAmount betAmount = new BetAmount(10000);
        Assertions.assertThat(betAmount.getValue()).isEqualTo(10000);
    }

    @Test
    void throwExceptionWhenValueIsLessThan() {
        Assertions.assertThatThrownBy(() -> new BetAmount(1))
                .isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    void throwExceptionWhenValueIsGreaterThan() {
        Assertions.assertThatThrownBy(() -> new BetAmount(120000))
                .isInstanceOf(IllegalArgumentException.class);
    }
}