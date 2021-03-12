package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MoneyTest {
    @DisplayName("같은 금액의 Money 인스턴스는 같은 객체로 간주한다.")
    @Test
    void equalsTest() {
        Money money = Money.of(10000);

        assertThat(money).isEqualTo(Money.of(10000));
    }

    @DisplayName("음수가 인수로 주어지면 예외를 발생시킨다.")
    @Test
    void throwExceptionOnNegativeNumber() {
        assertThatThrownBy(() -> Money.of(-1)).isInstanceOf(IllegalArgumentException.class);
    }
}