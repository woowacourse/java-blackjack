package domain.participant;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @Test
    void 금액을_증가한다() {
        // given
        Money money = Money.of(1000);

        // when
        money.increase(1000);

        // then
        Assertions.assertThat(money).isEqualTo(Money.of(2000));
    }

    @Test
    void 금액을_감소한다() {
        // given
        Money money = Money.of(2000);

        // when
        money.decrease(1000);

        // then
        Assertions.assertThat(money).isEqualTo(Money.of(1000));
    }
}
