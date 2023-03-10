package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {
    @Test
    void create() {
        assertDoesNotThrow(() -> new Money(10000));
    }

    @Test
    void createByString() {
        assertDoesNotThrow(() -> Money.of("10000"));
    }

    @Test
    void throwExceptionWhenCreateByInvalidString() {
        assertThatThrownBy(() -> Money.of("hihi"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void getValue() {
        Money betAmount = new Money(10000);
        assertThat(betAmount.getValue()).isEqualTo(10000);
    }

    @Test
    void throwExceptionWhenValueIsLessThan() {
        assertThatThrownBy(() -> new Money(1))
                .isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    void throwExceptionWhenValueIsGreaterThan() {
        assertThatThrownBy(() -> new Money(120000))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("곱하기 연산이 가능하다.")
    @Test
    void productOf() {
        Money money = new Money(10000);
        Money productedMoney = money.product(1.5);
        assertThat(productedMoney.getValue()).isEqualTo(15000);
    }
}