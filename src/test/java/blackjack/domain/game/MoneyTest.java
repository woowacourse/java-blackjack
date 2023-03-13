package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class MoneyTest {

    @Test
    void 베팅_금액은_100_미만은_예외가_발생한다() {
        //given
        final int bet = 90;

        //then
        assertThatThrownBy(() -> Money.betting(bet))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 베팅_금액은_10000_초과는_예외가_발생한다() {
        //given
        final int bet = 10_100;

        //then
        assertThatThrownBy(() -> Money.betting(bet))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 베팅_금액은_100_단위가_아니면_예외가_발생한다() {
        //given
        final int bet = 1001;

        //then
        assertThatThrownBy(() -> Money.betting(bet))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 베팅_금액은_비율을_받아_() {
        //given
        final double ratio = 1.5;

        //when
        Money multipliedMoney = Money.betting(1000).multiply(ratio);

        //then
        assertThat(multipliedMoney).isEqualTo(Money.betting(1500));
    }
}
