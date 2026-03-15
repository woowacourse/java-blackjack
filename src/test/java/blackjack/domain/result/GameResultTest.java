package blackjack.domain.result;

import blackjack.domain.money.Money;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameResultTest {

    @Test
    void 게임_결과가_승리라면_베팅_금액만큼_수익을_얻는다() {
        Money wager = new Money(10000);

        GameResult win = GameResult.WIN;
        Money profit = win.profitOf(wager);
        assertThat(profit).isEqualTo(wager);
    }

    @Test
    void 게임_결과가_패배라면_베팅_금액만큼_수익을_잃는다() {
        Money wager = new Money(10000);

        GameResult lose = GameResult.LOSE;
        Money profit = lose.profitOf(wager);
        assertThat(profit).isEqualTo(wager.negate());
    }

    @Test
    void 게임_결과가_무승부라면_베팅_금액에_상관없이_수익이_0이다() {
        Money wager = new Money(10000);

        GameResult lose = GameResult.DRAW;
        Money profit = lose.profitOf(wager);
        assertThat(profit).isEqualTo(new Money(0));
    }

    @Test
    void 게임_결과가_블랙잭이라면_베팅_금액의_150_퍼센트의_수익이_얻는다() {
        Money wager = new Money(10000);

        GameResult blackjack = GameResult.BLACKJACK;
        Money profit = blackjack.profitOf(wager);
        assertThat(profit).isEqualTo(wager.multiply(1.5));
    }
}