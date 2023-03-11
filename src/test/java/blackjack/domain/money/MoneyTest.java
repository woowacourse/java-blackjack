package blackjack.domain.money;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @Test
    @DisplayName("수익률을 넣으면, 소수점을 제외한 수익금을 반환한다.")
    void multiply() {
        final Double profitRate = 1.5;
        final Money money = new Money(1000);

        final Money profit = money.multiply(profitRate);

        assertThat(profit.getValue()).isEqualTo(1500);
    }
}
