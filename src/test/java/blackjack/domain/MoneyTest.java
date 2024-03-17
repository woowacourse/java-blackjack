package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.result.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {
    @Test
    @DisplayName("돈을 곱할 수 있다.")
    void calculateBlackJackBonusTest() {
        Money money = new Money(1000);

        assertThat(money.multiply(1.5)).isEqualTo(new Money(1500));
    }
}