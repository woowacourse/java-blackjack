package blackjack.model.player;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {

    @Test
    @DisplayName("돈 금액 반전")
    void moneyNegate() {
        Money money = new Money(new BigDecimal("1000"));
        assertThat(money.negate()).isEqualTo(new Money(new BigDecimal("-1000")));
    }

    @Test
    @DisplayName("돈 금액 곱하기")
    void multiply() {
        Money money = new Money(new BigDecimal("1500"));
        assertThat(money.multiply(new BigDecimal("2"))).isEqualTo(new Money(new BigDecimal("3000")));
    }

    @Test
    @DisplayName("돈 금액 추가")
    void add() {
        Money money1 = new Money(new BigDecimal("1000"));
        Money money2 = new Money(new BigDecimal("2000"));

        assertThat(money1.add(money2)).isEqualTo(new Money(new BigDecimal("3000")));
    }
}
