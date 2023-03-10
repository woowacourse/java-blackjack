package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import domain.game.Money;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @Test
    void create() {
        assertAll(
                () -> assertDoesNotThrow(() -> new Money(100)),
                () -> assertThatThrownBy(() -> new Money(0))
                        .isInstanceOf(IllegalArgumentException.class),
                () -> assertThatThrownBy(() -> new Money(-10))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }
}
