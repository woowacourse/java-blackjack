package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        assertThatCode(() -> new Money(1000))
                .doesNotThrowAnyException();
    }

    @DisplayName("돈을 더할 수 있다.")
    @Test
    void add() {
        Money money = new Money(1000);
        Money other = new Money(1000);

        Money result = money.add(other);

        assertThat(result.getAmount()).isEqualTo(2000);
    }

    @DisplayName("돈을 곱할 수 있다.")
    @Test
    void multiply() {
        Money money = new Money(1000);

        Money result = money.multiply(1.5);

        assertThat(result.getAmount()).isEqualTo(1500);
    }

    @DisplayName("돈을 뺄 수 있다.")
    @Test
    void minus() {
        Money money = new Money(1000);
        Money other = new Money(1000);

        Money result = money.minus(other);

        assertThat(result.getAmount()).isEqualTo(0);
    }
}
