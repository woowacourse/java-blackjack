package blackjack.domain.participants;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.game.GameResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BetTest {
    private static final long BET_AMOUNT = 1000;
    private static final long BLACKJACK_PAYOUT_NUMERATOR = 3;
    private static final long BLACKJACK_PAYOUT_DENOMINATOR = 2;

    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    void 금액이_양수가_아니면_예외를_던진다(int amount) {
        assertThatThrownBy(() -> new Bet(amount))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 플레이어가_블랙잭이면_배팅금의_1_5배의_수익를_반환한다() {
        // given
        Bet bet = new Bet(BET_AMOUNT);
        GameResult gameResult = GameResult.PLAYER_BLACKJACK;
        // when
        long profit = bet.calculateProfit(gameResult);
        // then
        assertThat(profit).isEqualTo(
            BET_AMOUNT * BLACKJACK_PAYOUT_NUMERATOR / BLACKJACK_PAYOUT_DENOMINATOR);
    }

    @Test
    void 결과가_무승부라면_0원의_수익을_반환한다() {
        // given
        Bet bet = new Bet(BET_AMOUNT);
        GameResult gameResult = GameResult.PUSH;
        // when
        long profit = bet.calculateProfit(gameResult);
        // then
        assertThat(profit).isEqualTo(0L);
    }

    @Test
    void 딜러가_승리하면_배팅금만큼_손실한_금액을_반환한다() {
        // given
        Bet bet = new Bet(BET_AMOUNT);
        GameResult gameResult = GameResult.DEALER_WIN;
        // when
        long profit = bet.calculateProfit(gameResult);
        // then
        assertThat(profit).isEqualTo(BET_AMOUNT * -1);
    }

    @Test
    void 플레이어가_승리하면_배팅_금액과_동일한_금액의_수익을_반환한다() {
        // given
        Bet bet = new Bet(BET_AMOUNT);
        GameResult gameResult = GameResult.PLAYER_WIN;
        // when
        long profit = bet.calculateProfit(gameResult);
        // then
        assertThat(profit).isEqualTo(BET_AMOUNT);
    }
}