package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {

    @Test
    @DisplayName("금액을 1.5배 늘린다.")
    void multiply() {
        Money money = new Money(1000);

        Money result = money.multiply(1.5);

        Assertions.assertThat(result).isEqualTo(new Money(1500));
    }

    @Test
    @DisplayName("금액에 마이너스부호를 붙인다.")
    void negative() {
        Money money = new Money(1000);

        Money result = money.negative();

        Assertions.assertThat(result).isEqualTo(new Money(-1000));
    }

}
