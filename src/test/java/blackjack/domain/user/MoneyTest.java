package blackjack.domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MoneyTest {
    @DisplayName("Money 객체를 생성한다.")
    @Test
    public void createMoney() {
        Money money = new Money(10000);

        assertThat(money).isInstanceOf(Money.class);
    }
}
