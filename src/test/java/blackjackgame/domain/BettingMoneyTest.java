package blackjackgame.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BettingMoneyTest {
    @DisplayName("게스트가 이길 때 베팅금액만큼 수익이 나는지 확인한다.")
    @Test
    void Should_ReturnBettingMoney_When_GuestWin() {
        BettingMoney bettingMoney = BettingMoney.of(10000);
        assertThat(bettingMoney.getRevenue(GameOutcome.WIN)).isEqualTo(10000);
    }

    @DisplayName("게스트가 블랙잭으로 이길 때 베팅금액의 1.5배만큼 수익이 나는지 확인한다.")
    @Test
    void Should_ReturnOnePointFiveTimesBettingMoney_When_GuestBlackJackWin() {
        BettingMoney bettingMoney = BettingMoney.of(10000);
        assertThat(bettingMoney.getRevenue(GameOutcome.BLACKJACK_WIN)).isEqualTo((int)(10000 * 1.5));
    }

    @DisplayName("게스트가 질 때 베팅금액만큼 수익을 잃는지 확인한다.")
    @Test
    void Should_LoseBettingMoney_When_GuestLose() {
        BettingMoney bettingMoney = BettingMoney.of(10000);
        assertThat(bettingMoney.getRevenue(GameOutcome.LOSE)).isEqualTo(10000 * -1);
    }

    @DisplayName("게스트가 비길 때 아무런 수익이 나지 않는지 확인한다.")
    @Test
    void Should_ReturnZero_When_GuestDraw() {
        BettingMoney bettingMoney = BettingMoney.of(10000);
        assertThat(bettingMoney.getRevenue(GameOutcome.DRAW)).isEqualTo(0);
    }
}
