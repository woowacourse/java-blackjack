package blackjack.domain.result;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BlackjackProfitTest {

    @Test
    @DisplayName("블랙잭으로 이긴 경우 수익 테스트")
    void calculateWinBlackjackProfit() {
        BlackjackProfit blackjackProfit = BlackjackProfit.of(BlackjackMatch.WIN_BLACKJACK, new BettingMoney(1000));

        assertThat(blackjackProfit.getProfit()).isEqualTo(1500);
    }

    @Test
    @DisplayName("블랙잭으로 진 경우 수익 테스트")
    void calculateLoseBlackjackProfit() {
        BlackjackProfit blackjackProfit = BlackjackProfit.of(BlackjackMatch.LOSE_BLACK_JACK, new BettingMoney(1000));

        assertThat(blackjackProfit.getProfit()).isEqualTo(-1500);
    }

    @Test
    @DisplayName("이긴 경우 수익 테스트")
    void calculateWinProfit() {
        BlackjackProfit blackjackProfit = BlackjackProfit.of(BlackjackMatch.WIN, new BettingMoney(1000));

        assertThat(blackjackProfit.getProfit()).isEqualTo(1000);
    }

    @Test
    @DisplayName("비긴 경우 수익 테스트")
    void calculateDrawProfit() {
        BlackjackProfit blackjackProfit = BlackjackProfit.of(BlackjackMatch.DRAW, new BettingMoney(1000));

        assertThat(blackjackProfit.getProfit()).isEqualTo(0);
    }

    @Test
    @DisplayName("진 경우 수익 테스트")
    void calculateLoseProfit() {
        BlackjackProfit blackjackProfit = BlackjackProfit.of(BlackjackMatch.LOSE, new BettingMoney(1000));

        assertThat(blackjackProfit.getProfit()).isEqualTo(-1000);
    }
}