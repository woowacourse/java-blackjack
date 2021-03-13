package blackjack.domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MoneyTest {
    @Test
    @DisplayName("돈을 나타나는 객체를 생성한다.")
    void create() {
        Money money = new Money(10000);

        assertThat(money).isInstanceOf(Money.class);
    }

    @Test
    @DisplayName("0보다 작은 금액 입력시 예외가 발생한다.")
    void creatException() {
        assertThatThrownBy(() -> {
            Money money = new Money(-3);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("금액을 더한다.")
    void sum() {
        Money money1 = new Money(1000);
        Money money2 = new Money(2000);

        Money sumMoney = money1.sum(money2);
        long value = sumMoney.getValue();

        assertThat(value).isEqualTo(3000);
    }

    @Test
    @DisplayName("금액에 실수를 곱해 수익을 구한다.")
    void calculateProfit() {
        Money money = new Money(1000);

        long resultMoney = money.calculateProfit(1.5);

        assertThat(resultMoney).isEqualTo(1500);
    }
}
