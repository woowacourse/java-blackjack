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
    void 베팅_금액은_100_000_초과는_예외가_발생한다() {
        //given
        final int bet = 100_100;

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
    void 베팅_금액은_게임결과를_받아_상금을_계산한다() {
        //given
        final GameResult result = GameResult.BLACKJACK;
        final Money money = Money.betting(1000);

        //when
        Money multipliedMoney = money.calculatePrize(result);

        //then
        assertThat(multipliedMoney.getValue()).isEqualTo(Money.betting(1500).getValue());
    }
}
