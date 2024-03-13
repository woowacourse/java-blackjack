package domain.money;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {
    @DisplayName("금액을 더한 값을 반환한다.")
    @Test
    void addMoney() {
        Money money = new Money(1000);
        Money addition = money.add(new Money(2000));
        assertThat(addition).isEqualTo(new Money(3000));
    }
}
