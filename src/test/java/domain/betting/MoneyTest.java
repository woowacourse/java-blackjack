package domain.betting;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MoneyTest {
    @Test
    @DisplayName("베팅 액수는 양수이다.")
    void validValueTest() {
        Money money = new Money(1000);
        long value = money.getValue();
        assertThat(value).isEqualTo(1000);
    }

    @Test
    @DisplayName("배팅 액수가 양수가 아니면 오류를 반환한다.")
    void invalidValueTest() {
        assertThrows(IllegalArgumentException.class, () -> new Money(-1000));
    }

    @Test
    @DisplayName("금액을 합산할 수 있어야 한다.")
    void addTest() {
        Money money = new Money(1000);
        Money other = new Money(2000);

        assertThat(money.sum(other)).isEqualTo(new Money(3000));
    }
}
