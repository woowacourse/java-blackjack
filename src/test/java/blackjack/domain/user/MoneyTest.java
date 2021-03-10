package blackjack.domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {

    @Test
    @DisplayName("수익 계산 확인")
    void moneyEarning() {
        Money money = new Money(1000);
        assertThat(money.getEarning(1.5)).isEqualTo(1500);
    }

    @Test
    @DisplayName("같은 금액이면 동일 인스턴스인지 확인")
    void moneyInstance() {
        Money money = new Money(1000);
        assertThat(money).isEqualTo(new Money(1000));
    }
}
