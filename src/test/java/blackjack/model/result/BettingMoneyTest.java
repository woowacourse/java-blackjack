package blackjack.model.result;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BettingMoneyTest {
    @Test
    @DisplayName("플레이어가 승리하면 베팅 금액만큼 금액을 더 받는다")
    void determineWinnerFinalBettingMoneyTest() {
        // given
        BettingMoney bettingMoney = new BettingMoney(1000);

        // when
        BettingMoney finalBettingMoney = bettingMoney.fixByMatchResult(MatchResult.WIN);

        // then
        assertThat(finalBettingMoney.getAmount()).isEqualTo(2000);
    }

    @Test
    @DisplayName("플레이어가 지면 베팅 금액을 잃는다")
    void determineLoserFinalBettingMoneyTest() {
        // given
        BettingMoney bettingMoney = new BettingMoney(1000);

        // when
        BettingMoney finalBettingMoney = bettingMoney.fixByMatchResult(MatchResult.LOSE);

        // then
        assertThat(finalBettingMoney.getAmount()).isEqualTo(0);
    }
}
