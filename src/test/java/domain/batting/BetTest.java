package domain.batting;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.result.GameResult;
import org.junit.jupiter.api.Test;

class BetTest {

    @Test
    void 배팅_금액을_생성한다() {
        // given
        int money = 10000;

        // when & then
        assertThatCode(() -> Bet.of(money))
                .doesNotThrowAnyException();
    }

    @Test
    void 만약_음수가_입력되면_예외가_발생한다() {
        // given
        int money = -1000;

        // when & then
        assertThatThrownBy(() -> Bet.of(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("배팅 금액은 음수나 0이 될 수 없습니다.");
    }

    @Test
    void 만약_0이_입력되면_예외가_발생한다() {
        // given
        int money = 0;

        // when & then
        assertThatThrownBy(() -> Bet.of(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("배팅 금액은 음수나 0이 될 수 없습니다.");
    }

    @Test
    void 십_만원_초과의_금액_배팅_시_예외_발생() {
        // given
        int money = 100_001;

        // when & then
        assertThatThrownBy(() -> Bet.of(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("배팅 가능 금액은 최대 10만원 입니다.");
    }

    @Test
    void 패에_대한_손실_수익_반환() {
        // given
        GameResult lose = GameResult.LOSE;
        Bet bet = Bet.of(10000);

        // when
        int profit = bet.calculateProfit(lose);

        // then
        assertThat(profit).isEqualTo(-10000);
    }

    @Test
    void 승에_대한_손실_수익_반환() {
        // given
        GameResult lose = GameResult.WIN;
        Bet bet = Bet.of(10000);

        // when
        int profit = bet.calculateProfit(lose);

        // then
        assertThat(profit).isEqualTo(10000);
    }

    @Test
    void 무에_대한_손실_수익_반환() {
        // given
        GameResult lose = GameResult.DRAW;
        Bet bet = Bet.of(10000);

        // when
        int profit = bet.calculateProfit(lose);

        // then
        assertThat(profit).isEqualTo(0);
    }

    @Test
    void 블랙잭_승에_대한_손실_수익_반환() {
        // given
        GameResult blackjackWin = GameResult.BLACKJACK_WIN;
        Bet bet = Bet.of(10000);

        // when
        int profit = bet.calculateProfit(blackjackWin);

        // then
        assertThat(profit).isEqualTo(15000);
    }
}
