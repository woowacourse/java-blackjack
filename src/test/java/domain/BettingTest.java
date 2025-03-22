package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import domain.card.Betting;
import java.util.function.Function;
import org.junit.jupiter.api.Test;

public class BettingTest {

    @Test
    void 배팅_객체를_생성한다() {
        //given
        final int amount = 10_000;

        //when
        final Betting betting = new Betting(amount);

        //then
        assertThat(betting).isInstanceOf(Betting.class);
    }

    @Test
    void 배팅_금액이_음수이므로_예외가_발생한다() {
        //given
        final int amount = -1;

        //when
        Function<Integer, Betting> function = Betting::new;

        //then
        assertThatIllegalArgumentException().isThrownBy(() -> function.apply(amount));
    }

    @Test
    void 배팅_게임_결과에_따라_수익률을_계산한다() {
        //given
        final int amount = 10_000;
        final GameResult gameResult1 = GameResult.LOSE;
        final GameResult gameResult2 = GameResult.WIN;
        final GameResult gameResult3 = GameResult.DRAW;
        final boolean isBlackjack = true;
        final boolean isNotBlackjack = false;

        //when
        final Betting betting = new Betting(amount);
        final double result1 = betting.calculateBettingOfReturn(gameResult1, isNotBlackjack);
        final double result2 = betting.calculateBettingOfReturn(gameResult2, isBlackjack);
        final double result3 = betting.calculateBettingOfReturn(gameResult3, isNotBlackjack);
        final double result4 = betting.calculateBettingOfReturn(gameResult2, isNotBlackjack);

        //then
        assertThat(result1).isEqualTo(-10_000);
        assertThat(result2).isEqualTo(15_000);
        assertThat(result3).isZero();
        assertThat(result4).isEqualTo(10_000);
    }
}
