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
    @DisplayName("베팅 액수가 양수가 아니면 오류를 반환한다.")
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

    @Test
    @DisplayName("금액은 1000원 단위로 베팅할 수 있다.")
    void bettingUnitTest() {
        assertThrows(IllegalArgumentException.class, () -> new Money(10231230));
    }

    @Test
    @DisplayName("최대 베팅 액수는 1억이다.")
    void maximumMoneyTest() {
        Money money = new Money(100_000_000);
        assertThat(money.getValue()).isEqualTo(100_000_000);
    }

    @Test
    @DisplayName("최대 베팅 액수가 넘어가면 오류를 반환한다.")
    void invalidMaximumMoneyTest() {
        assertThrows(IllegalArgumentException.class, () -> new Money(100_001_000));
    }

}
