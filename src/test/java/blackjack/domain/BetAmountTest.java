package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class BetAmountTest {
    @Test
    void create() {
        assertDoesNotThrow(() -> new BetAmount(10000));
    }

    @Test
    void createByString() {
        assertDoesNotThrow(() -> BetAmount.of("10000"));
    }

    @Test
    void throwExceptionWhenCreateByInvalidString() {
        assertThatThrownBy(() -> BetAmount.of("hihi"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void getValue() {
        BetAmount betAmount = new BetAmount(10000);
        Assertions.assertThat(betAmount.getValue()).isEqualTo(10000);
    }

    @Test
    void throwExceptionWhenValueIsLessThan() {
        assertThatThrownBy(() -> new BetAmount(1))
                .isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    void throwExceptionWhenValueIsGreaterThan() {
        assertThatThrownBy(() -> new BetAmount(120000))
                .isInstanceOf(IllegalArgumentException.class);
    }
}