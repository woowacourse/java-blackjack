package blackjack.model.result;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BettingMoneyTest {
    @Test
    @DisplayName("승리하면 베팅 금액만큼 금액을 더 받는다")
    void fixByMatchResultTest() {
        // given
        BettingMoney bettingMoney = new BettingMoney(1000);

        // when
        BettingMoney finalBettingMoney = bettingMoney.fixByMatchResult(MatchResult.WIN);

        // then
        assertThat(finalBettingMoney.getAmount()).isEqualTo(2000);
    }
}
