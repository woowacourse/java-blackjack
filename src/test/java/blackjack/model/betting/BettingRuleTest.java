package blackjack.model.betting;


import static blackjack.model.fixture.PlayerFixture.BLACKJACK_PLAYER;
import static blackjack.model.fixture.PlayerFixture.NOT_BLACKJACK_21_PLAYER;
import static blackjack.model.betting.ProfitRate.LOSE_RATE;
import static blackjack.model.betting.ProfitRate.NOT_BLACKJACK_BUT_WIN_RATE;
import static blackjack.model.betting.ProfitRate.BLACKJACK_RATE;
import static blackjack.model.betting.ProfitRate.DRAW_RATE;
import static blackjack.model.result.ResultCommand.DRAW;
import static blackjack.model.result.ResultCommand.LOSE;
import static blackjack.model.result.ResultCommand.WIN;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BettingRuleTest {

    @Nested
    class whenPlayerWin {

        @Test
        @DisplayName("플레이어가 BlackJack으로 승리한 경우, 배팅 금액의 1.5배를 준다.")
        void calculateProfitRateByBlackJack() {
            BettingRule bettingRule = new BettingRule();
            assertThat(bettingRule.calculateProfitRate(BLACKJACK_PLAYER.getPlayer(), WIN)).isEqualTo(BLACKJACK_RATE);
        }

        @Test
        @DisplayName("플레이어가 승리한 경우, 배팅 금액의 1배를 준다.")
        void calculateProfitRateByDealerNotBust() {
            BettingRule bettingRule = new BettingRule();
            assertThat(bettingRule.calculateProfitRate(NOT_BLACKJACK_21_PLAYER.getPlayer(), WIN)).isEqualTo(
                    NOT_BLACKJACK_BUT_WIN_RATE);
        }
    }

    @Test
    @DisplayName("플레이어가 무승부인 경우, 배팅 금액의 0배를 준다.")
    void calculateProfitRateWhenDraw() {
        BettingRule bettingRule = new BettingRule();
        assertThat(bettingRule.calculateProfitRate(NOT_BLACKJACK_21_PLAYER.getPlayer(), DRAW)).isEqualTo(DRAW_RATE);
    }

    @Test
    @DisplayName("플레이어가 지는 경우, 베팅 금액만큼을 잃는다.")
    void calculateProfitRateWhenPlayerLose() {
        BettingRule bettingRule = new BettingRule();
        assertThat(bettingRule.calculateProfitRate(NOT_BLACKJACK_21_PLAYER.getPlayer(), LOSE))
                .isEqualTo(LOSE_RATE);
    }
}
