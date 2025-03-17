package blackjack.result;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class BettingTest {

    @Test
    @DisplayName("초기 배팅 금액은 음수가 될 수 없다")
    void shouldNotAllowNegativeBettingAmount() {
        // given
        int negativeAmount1 = -10000;
        int negativeAmount2 = -1;

        // when
        // then
        assertAll(() -> {
            assertThatThrownBy(() -> Betting.from(negativeAmount1))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("금액은 음수가 될 수 없습니다.");

            assertThatThrownBy(() -> Betting.from(negativeAmount2))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("금액은 음수가 될 수 없습니다.");
        });
    }

    @Test
    @DisplayName("초기 배팅 금액은 0원이 될 수 있다")
    void shouldAllowZeroBettingAmount() {
        // given
        int zeroAmount = 0;

        // when
        // then
        assertThatCode(() -> {
            Betting betting = Betting.from(zeroAmount);
        })
                .doesNotThrowAnyException();
    }

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
