package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        assertThatCode(() -> new Money(1000))
                .doesNotThrowAnyException();
    }

    @DisplayName("돈은 0보다 커야 한다.")
    @Test
    void validateMoneyRange() {
        assertThatThrownBy(() -> new Money(0))
                .isInstanceOf(IllegalArgumentException.class);
    }


    @DisplayName("같은 금액을 가지면 같은 객체이다.")
    @Test
    void equals() {
        Money money1 = new Money(1000);
        Money money2 = new Money(1000);

        assertThat(money1).isEqualTo(money2);
    }

    @DisplayName("돈을 곱할 수 있다.")
    @Test
    void multiply() {
        Money money = new Money(1000);

        Money result = money.multiply(2);

        assertThat(result).isEqualTo(new Money(2000));
    }
}
