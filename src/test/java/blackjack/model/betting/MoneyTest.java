package blackjack.model.betting;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {
    @Test
    @DisplayName("곱셈한 금액을 구한다.")
    void multiple() {
        Money money = new Money(1_000);
        assertThat(money.multiple(ProfitRate.BLACKJACK_RATE)).isEqualTo(new Money(1_500));
    }
}
