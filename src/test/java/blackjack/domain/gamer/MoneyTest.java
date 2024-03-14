package blackjack.domain.gamer;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @Test
    @DisplayName("금액이 같으면 같은 객체로 취급한다.")
    void equalsTest() {
        assertThat(new Money(10000)).isEqualTo(new Money(10000));
    }

    @Test
    @DisplayName("금액을 더한다.")
    void sumTest() {
        Money money = new Money(10000);

        assertThat(money.add(new Money(10000))).isEqualTo(new Money(20000));
    }

    @Test
    @DisplayName("금액을 비율만큼 곱한다.")
    void multiplyTest() {
        Money money = new Money(10000);

        assertThat(money.multiplyByRatio(1.5)).isEqualTo(new Money(15000));
    }
}
