package blackjack.domain.game;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class MoneyTest {

    @Test
    void 돈은_더할_수_있다() {
        //given
        Money money = new Money(1);

        //when
        money = money.add(new Money(2));

        //then
        Assertions.assertThat(money.getValue()).isEqualTo(3);
    }

    @Test
    void 돈은_곱할_수_있다() {
        //given
        Money money = new Money(2);

        //when
        money = money.multiple(BigDecimal.valueOf(6));

        //then
        Assertions.assertThat(money.getValue()).isEqualTo(12);
    }
}
