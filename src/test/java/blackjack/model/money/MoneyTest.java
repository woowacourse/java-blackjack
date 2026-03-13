package blackjack.model.money;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @Test
    @DisplayName("뺄셈 테스트")
    void minusTest() {
        // given
        Money money10k = Money.of(BigDecimal.valueOf(10000));
        Money money3k = Money.of(BigDecimal.valueOf(3000));

        // when
        Money result = money10k.minus(money3k);

        // then
        assertThat(result).isEqualTo(Money.of(BigDecimal.valueOf(7000)));
    }

    @Test
    @DisplayName("곱셈 테스트")
    void multiplyTest() {
        // given
        Money money = Money.of(BigDecimal.valueOf(10000));

        // when
        Money result = money.multiply(BigDecimal.valueOf(1.5));

        // then
        assertThat(result).isEqualTo(Money.of(BigDecimal.valueOf(15000.0)));
    }
}