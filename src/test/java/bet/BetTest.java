package bet;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import result.MatchResult;

class BetTest {

    @ParameterizedTest
    @ValueSource(ints = {0, -1000, 100_001_000})
    void 베팅_금액이_0_또는_음수이거나_1억원보다_큰_경우_예외가_발생한다(int value) {
        Assertions.assertThatThrownBy(() -> new Bet(value))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 베팅_금액을_추가한다() {
        final int amount = 10000;

        Assertions.assertThatNoException()
                .isThrownBy(() -> new Bet(amount));
    }

    @Test
    void 승리한_베팅_결과를_계산한다() {
        final int amount = 10000;
        final int expected = 10000;

        Bet bet = new Bet(amount);
        bet.calculateBettingResult(MatchResult.WIN);

        Assertions.assertThat(bet.getAmount())
                .isEqualTo(expected);
    }

    @Test
    void 블랙잭으로_승리한_베팅_결과를_계산한다() {
        final int amount = 10000;
        final int expected = 15000;

        Bet bet = new Bet(amount);
        bet.calculateBettingResult(MatchResult.WIN_WITH_BLACKJACK);

        Assertions.assertThat(bet.getAmount())
                .isEqualTo(expected);
    }

    @Test
    void 무승부인_플레이어의_베팅_결과를_계산한다() {
        final int amount = 10000;
        final int expected = 0;

        Bet bet = new Bet(amount);
        bet.calculateBettingResult(MatchResult.DRAW);

        Assertions.assertThat(bet.getAmount())
                .isEqualTo(expected);
    }

    @Test
    void 패배한_플레이어의_베팅_결과를_계산한다() {
        final int amount = 10000;
        final int expected = -10000;

        Bet bet = new Bet(amount);
        bet.calculateBettingResult(MatchResult.LOSE);

        Assertions.assertThat(bet.getAmount())
                .isEqualTo(expected);
    }
}