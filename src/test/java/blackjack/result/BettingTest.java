package blackjack.result;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class BettingTest {

    @Test
    @DisplayName("수익률을 적용하고, 원금을 제외한 수익만을 계산할 수 있다")
    void canApplyProfit() {
        // given
        Betting betting = Betting.from(10000);

        // when
        Money whenWin = betting.applyProfit(GameResult.WIN);
        Money whenBlackjack = betting.applyProfit(GameResult.BLACKJACK);
        Money whenDraw = betting.applyProfit(GameResult.DRAW);
        Money whenLose = betting.applyProfit(GameResult.LOSE);

        // then
        assertAll(() -> {
            assertThat(whenWin).isEqualTo(Money.from(10000));
            assertThat(whenBlackjack).isEqualTo(Money.from(5000));
            assertThat(whenDraw).isEqualTo(Money.from(0));
            assertThat(whenLose).isEqualTo(Money.from(-10000));
        });
    }

}
