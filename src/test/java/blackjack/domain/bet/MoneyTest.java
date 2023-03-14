package blackjack.domain.bet;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class MoneyTest {

    @Test
    void getNegativeMoney() {
        Money money = new Money(10000);
        assertThat(money.getNegativeMoney().getValue()).isEqualTo(-10000);
    }
}
