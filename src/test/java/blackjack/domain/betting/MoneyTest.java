package blackjack.domain.betting;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MoneyTest {
    @Test
    void multiplyTest() {
        Money money = new Money(1000);
        assertThat(money.multiply(1.5)).isEqualTo(new Money(1500));
    }
}