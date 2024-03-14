package domain;

import java.math.BigDecimal;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @Test
    @DisplayName("금액은 BigDecimal 자료형으로 초기화 할 수 있다.")
    void createMoney(){
        Assertions.assertThatCode(() -> new Money(BigDecimal.valueOf(100)))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("금액의 양을 반환할 수 있다.")
    void getAmount() {
        Money money = new Money(BigDecimal.valueOf(100));
        Assertions.assertThat(money.getAmount()).isEqualTo(BigDecimal.valueOf(100));
    }
    @Test
    @DisplayName("금액은 더해서 새로운 금액을 만들 수 있다.")
    void add() {
        Money money1 = new Money(BigDecimal.valueOf(100));
        Money money2 = new Money(BigDecimal.valueOf(200));

        Money actual = money1.add(money2);

        Assertions.assertThat(actual).isEqualTo(new Money(BigDecimal.valueOf(300)));

    }

    @Test
    @DisplayName("금액을 차감해서 새로운 금액을 만들 수 있다.")
    void subtract() {
        Money money1 = new Money(BigDecimal.valueOf(100));
        Money money2 = new Money(BigDecimal.valueOf(200));

        Money actual = money1.subtract(money2);

        Assertions.assertThat(actual).isEqualTo(new Money(BigDecimal.valueOf(-100)));
    }


    @Test
    @DisplayName("현재 가진 금액의 비율을 곱해서 새로운 금액을 만들 수 있다.")
    void multiply() {
        Money money1 = new Money(BigDecimal.valueOf(100));

        Money actual = money1.multiply(BigDecimal.valueOf(1.5));

        Assertions.assertThat(actual).isEqualTo(new Money(BigDecimal.valueOf(150.0)));
    }
}