package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class BetTest {

    @Test
    void 최소_베팅_금액보다_적게_베팅하면_예외가_발생한다() {
        // given
        final int bet = Bet.MIN_BET - 1;

        // when & then
        Assertions.assertThatThrownBy(() -> {
            new Bet(bet);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 최대_베팅_금액보다_많이_베팅하면_예외가_발생한다() {
        // given
        final int bet = Bet.MAX_BET + 1;

        // when & then
        Assertions.assertThatThrownBy(() -> {
            new Bet(bet);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}