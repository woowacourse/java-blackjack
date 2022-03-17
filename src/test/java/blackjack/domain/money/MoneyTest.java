package blackjack.domain.money;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @Test
    @DisplayName("금액을 생성한다.")
    public void createMoney() {
        // given
        int amount = 1000;

        // when
        Money money = new Money(amount);

        // then
        assertThat(money.getAmount()).isEqualTo(1000);
    }

    @Test
    @DisplayName("문자열을 기반으로 금액을 생성한다.")
    public void createMoneyWithString() {
        // given
        String input = "1000";

        // when
        Money money = new Money(input);

        // then
        assertThat(money.getAmount()).isEqualTo(1000);
    }

    @Test
    @DisplayName("0원 미만은 생성할 수 없다.")
    public void throwsExceptionWithNegativeInteger() {
        // given & when
        int amount = -1;
        // then
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> new Money(amount));
    }

    @Test
    @DisplayName("금액을 곱한다.")
    public void multiplyMoney() {
        // given
        double rate = 1.5;
        Money money = new Money(1000);
        // when
        Money multiplied = money.multiply(rate);
        // then
        assertThat(multiplied.getAmount()).isEqualTo(1500);
    }
}