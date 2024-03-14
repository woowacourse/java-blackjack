package blackjack.model.betting;


import static blackjack.model.PlayerFixture.BLACKJACK_PLAYER;
import static blackjack.model.result.ResultCommand.WIN;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingRuleTest {

    @Test
    @DisplayName("플레이어가 BlackJack으로 승리한 경우, 배팅 금액의 1.5배를 준다.")
    void findProfitAmountWhen() {
        BettingRule bettingRule = new BettingRule();
        assertThat(bettingRule.calculateProfitRate(BLACKJACK_PLAYER.getPlayer(), WIN)).isEqualTo(1.5);
    }
}
