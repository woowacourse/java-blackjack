package bet;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import match.MatchResult;

class BetResultTest {

    @Test
    void 승리한_베팅_결과를_계산한다() {
        final int amount = 10000;
        final int expected = 10000;

        BetResult betResult = new BetResult(MatchResult.WIN, amount);

        Assertions.assertThat(betResult.getAmount())
                .isEqualTo(expected);
    }

    @Test
    void 블랙잭으로_승리한_베팅_결과를_계산한다() {
        final int amount = 10000;
        final int expected = 15000;

        BetResult betResult = new BetResult(MatchResult.WIN_WITH_BLACKJACK, amount);

        Assertions.assertThat(betResult.getAmount())
                .isEqualTo(expected);
    }

    @Test
    void 무승부인_플레이어의_베팅_결과를_계산한다() {
        final int amount = 10000;
        final int expected = 0;

        BetResult betResult = new BetResult(MatchResult.DRAW, amount);

        Assertions.assertThat(betResult.getAmount())
                .isEqualTo(expected);
    }

    @Test
    void 패배한_플레이어의_베팅_결과를_계산한다() {
        final int amount = 10000;
        final int expected = -10000;

        BetResult betResult = new BetResult(MatchResult.LOSE, amount);

        Assertions.assertThat(betResult.getAmount())
                .isEqualTo(expected);
    }
}
