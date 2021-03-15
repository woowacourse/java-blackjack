package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {

    @DisplayName("액수 같으면 같은 금액으로 본다.")
    @Test
    void create() {
        Money money = new Money(500);
        assertThat(money).isEqualTo(new Money(500));
    }

    @DisplayName("수익률을 이용해 얻거나 잃는 배팅금액을 계산한다.")
    @Test
    void multiply() {
        Money money = new Money(1000);
        assertThat(money.multiply(1.5)).isEqualTo(new Money(1500));
    }

    @DisplayName("인자로 들어온 값만큼 돈을 더한다.")
    @Test
    void sum() {
        Money money = new Money(1000);
        Money summing = new Money(2000);
        assertThat(money.sum(summing)).isEqualTo(new Money(3000));
    }

    @DisplayName("소수점 없이 정수값의 돈을 반환한다.")
    @Test
    void get() {
        Money money = new Money(500.00);
        assertThat(money.getMoney()).isEqualTo(500);
    }
}
