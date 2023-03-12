package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingAmountTest {

    @Test
    @DisplayName("초기 배팅 금액은 10000원 이상이 아니라면 예외가 발생한다.")
    void createBettingAmountFail() {
        int money = 9999;

        assertThatThrownBy(() -> BettingAmount.fromPlayer(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("초기 배팅 금액은 10000원 이상이어야 합니다.");
    }

    @Test
    @DisplayName("초기 배팅 금액이 10000원 이상이라면 배팅 금액이 생성된다.")
    void createBettingAmountSuccess() {
        int money = 10000;

        BettingAmount bettingAmount = BettingAmount.fromPlayer(money);

        assertThat(bettingAmount.getRevenue()).isEqualTo(money);
    }

    @Test
    @DisplayName("플레이어가 블랙잭일 경우, 자신의 배팅 금액의 1.5배를 돌려받는다.")
    void playerBlackJackBettingAmount() {
        BettingAmount initial = BettingAmount.fromPlayer(10000);

        BettingAmount result = initial.calculateMultiple(GameResult.BLACKJACK);

        assertThat(result.getRevenue()).isEqualTo(10000 * 1.5);
    }

    @Test
    @DisplayName("플레이어가 이긴 경우, 자신의 배팅 금액의 1배를 돌려받는다.")
    void playerWinBettingAmount() {
        BettingAmount initial = BettingAmount.fromPlayer(10000);

        BettingAmount result = initial.calculateMultiple(GameResult.WIN);

        assertThat(result.getRevenue()).isEqualTo(10000 * 1);
    }

    @Test
    @DisplayName("플레이어가 지거나 bust 인 경우, 자신의 배팅 금액의 1배를 잃는다.")
    void playerLoseOrBustBettingAmount() {
        BettingAmount initial = BettingAmount.fromPlayer(10000);

        BettingAmount result = initial.calculateMultiple(GameResult.LOSE);

        assertThat(result.getRevenue()).isEqualTo(10000 * -1);
    }

    @Test
    @DisplayName("딜러는 한 플레이어의 수익만큼의 손실, 또는 이득을 얻는다.")
    void dealerLoseGivePlayerBettingAmount() {
        BettingAmount dealerInitialAmount = BettingAmount.getDealerInitialAmount();
        BettingAmount playerInitialAmount = BettingAmount.fromPlayer(10000);

        BettingAmount dealerResult = dealerInitialAmount.subtract(playerInitialAmount);

        assertThat(dealerResult.getRevenue()).isEqualTo(dealerInitialAmount.getRevenue() - 10000);
    }

}
