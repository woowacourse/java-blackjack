package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameResultTest {

    @DisplayName("플레이어가 블랙잭일때 수익은 배팅한 금액의 1.5배이다")
    @Test
    void test_BlackjackWinningMoney() {
        // given
        BettingMoney bettingMoney = new BettingMoney(10000);
        GameResult blackjackWin = GameResult.BLACKJACK_WIN;

        // when
        int payout = blackjackWin.calculatePayout(bettingMoney);

        // then
        assertThat(payout).isEqualTo(15000);
    }

    @DisplayName("수익이 소수면 버리기 처리한다.")
    @Test
    void test_BlackjackWinningMoney_truncation() {
        // given
        BettingMoney bettingMoney = new BettingMoney(1);
        GameResult blackjackWin = GameResult.BLACKJACK_WIN;

        // when
        int payout = blackjackWin.calculatePayout(bettingMoney);

        // then
        assertThat(payout).isEqualTo(1);
    }

    @DisplayName("플레이어가 이겼을 때 배팅한 금액만큼 그대로 수익이된다.")
    @Test
    void test_winningMoney() {
        // given
        BettingMoney bettingMoney = new BettingMoney(10000);
        GameResult blackjackWin = GameResult.WIN;

        // when
        int payout = blackjackWin.calculatePayout(bettingMoney);

        // then
        assertThat(payout).isEqualTo(10000);
    }

    @DisplayName("플레이어가 졌을 때 배팅한 금액만큼 수익이 마이너스가 된다.")
    @Test
    void test_losingMoney() {
        // given
        BettingMoney bettingMoney = new BettingMoney(10000);
        GameResult blackjackWin = GameResult.LOSE;

        // when
        int payout = blackjackWin.calculatePayout(bettingMoney);

        // then
        assertThat(payout).isEqualTo(-10000);
    }

    @DisplayName("플레이어가 비겼을 때 수익도 손실도 없다.")
    @Test
    void test_drawMoney() {
        // given
        BettingMoney bettingMoney = new BettingMoney(10000);
        GameResult blackjackWin = GameResult.DRAW;

        // when
        int payout = blackjackWin.calculatePayout(bettingMoney);

        // then
        assertThat(payout).isEqualTo(0);
    }

}