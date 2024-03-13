package blackjack.domain;

import blackjack.domain.participants.Money;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {
    @Test
    @DisplayName(("돈을 더한다."))
    void addTest() {
        Money money = new Money(1000);
        money = money.add(new Money(3000));

        Assertions.assertThat(money.getValue()).isEqualTo(4000);
    }

    @Test
    @DisplayName(("돈을 뺀다."))
    void subtractTest() {
        Money money = new Money(3000);
        money = money.subtract(new Money(1000));

        Assertions.assertThat(money.getValue()).isEqualTo(2000);
    }

    @Test
    @DisplayName(("돈을 곱한다."))
    void multiplyTest() {
        Money money = new Money(3000);
        money = money.multiply(2);

        Assertions.assertThat(money.getValue()).isEqualTo(6000);
    }
}
