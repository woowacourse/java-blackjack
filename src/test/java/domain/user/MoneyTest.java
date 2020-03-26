package domain.user;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class MoneyTest {
    @Test
    void ofSucceed() {
        assertThat(Money.of(10)).isNotNull();
    }

    @Test
    void ofFail() {
        assertThatThrownBy(() -> Money.of(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Money.OUT_OF_RANGE_MESSGAE);
    }

    @Test
    void multiply() {
        Money money = Money.of(10);
        assertThat(money.multiply(1.5)).isEqualTo(Money.of(15));
    }
}