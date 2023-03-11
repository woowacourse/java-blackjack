package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.money.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {

    private Money money;

    @BeforeEach
    void setUp() {
        money = new Money(0);
    }

    @Test
    @DisplayName("돈을 더 받을 수 있다.")
    void addTest() {
        Money newMoney = money.add(new Money(10000));

        assertThat(newMoney.getMoney()).isEqualTo(10000);
    }

    @Test
    void subtractTest() {
        money = new Money(10000);

        Money newMoney = money.subtract(new Money(10000));

        assertThat(newMoney.getMoney()).isEqualTo(0);
    }
}
