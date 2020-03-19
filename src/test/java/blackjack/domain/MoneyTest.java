package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {

    @Test
    @DisplayName("생성자 테스트")
    void Money() {
        assertThat(Money.fromPositive("500"))
            .isInstanceOf(Money.class);

        assertThatThrownBy(() -> Money.fromPositive("-1000"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("음수");

        assertThatThrownBy(() -> Money.fromPositive("1234"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("최소 단위");
    }

    @Test
    @DisplayName("금액 더하기")
    void add() {
        Money money = Money.fromPositive("1000");
        assertThat(money.add(Money.fromPositive("3000")).getMoney()).isEqualTo(4000);
    }

    @Test
    @DisplayName("금액 곱하기")
    void multiply() {
        Money money = Money.fromPositive("1000");
        assertThat(money.multiply(1).getMoney()).isEqualTo(1000);
        assertThat(money.multiply(0).getMoney()).isEqualTo(0);
        assertThat(money.multiply(-1).getMoney()).isEqualTo(-1000);
        assertThat(money.multiply(1.5).getMoney()).isEqualTo(1500);
    }
}
