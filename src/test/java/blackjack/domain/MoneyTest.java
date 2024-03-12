package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.result.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @Test
    @DisplayName("돈을 더해줄 수 있다.")
    void addTest() {
        Money money = new Money(1000);
        Money other = new Money(1000);

        assertThat(money.add(other)).isEqualTo(new Money(2000));
    }

    @Test
    @DisplayName("돈을 뺄 수 있다.")
    void subTest() {
        Money money = new Money(1000);
        Money other = new Money(1000);

        assertThat(money.sub(other)).isEqualTo(new Money(0));
    }

    @Test
    @DisplayName("돈을 곱할 수 있다.")
    void calculateBlackJackBonusTest() {
        Money money = new Money(1000);

        assertThat(money.multiply(1.5)).isEqualTo(new Money(1500));
    }
}