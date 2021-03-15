package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @Test
    @DisplayName("값 객체 비교 - 성공")
    void equalsSuccess() {
        int value = 10000;
        Money money1 = new Money(value);
        Money money2 = new Money(value);

        assertThat(money1).isEqualTo(money2);
        assertThat(money1).hasSameHashCodeAs(money2);
    }

    @Test
    @DisplayName("값 객체 비교 - 실패")
    void equalsFail() {
        Money money1 = new Money(10000);
        Money money2 = new Money(20000);

        assertThat(money1).isNotEqualTo(money2);
        assertThat(money1.hashCode()).isNotEqualTo(money2.hashCode());
    }
}