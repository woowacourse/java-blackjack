package blackjack.domain.participants;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ProfitTest {
    private static final long BASE_AMOUNT = 1000L;
    private static final long BLACKJACK_PAYOUT_NUMERATOR = 3;
    private static final long BLACKJACK_PAYOUT_DENOMINATOR = 2;

    @Test
    void 수익_0원의_객체를_생성한다() {
        // given
        Profit profit = Profit.zero();
        // when & then
        assertThat(profit.value()).isEqualTo(0L);
    }

    @Test
    void 수익을_음수로_변환한다() {
        // given
        Profit profit = new Profit(BASE_AMOUNT);
        // when
        Profit negativeProfit = profit.negative();
        // then
        long expected = profit.value() * -1;
        assertThat(negativeProfit.value()).isEqualTo(expected);
    }

    @Test
    void 수익의_1_5배를_변환한다() {
        // given
        Profit profit = new Profit(BASE_AMOUNT);
        // when
        Profit blackjackProfit = profit.applyBlackjackPayout();
        // then
        long expected = profit.value() * BLACKJACK_PAYOUT_NUMERATOR / BLACKJACK_PAYOUT_DENOMINATOR;
        assertThat(blackjackProfit.value()).isEqualTo(expected);
        System.out.println(expected);
        System.out.println(blackjackProfit.value());
    }
}