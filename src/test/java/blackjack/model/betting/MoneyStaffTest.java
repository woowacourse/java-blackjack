package blackjack.model.betting;

import static blackjack.model.HandFixture.UNDER_16_HAND;
import static blackjack.model.PlayerFixture.BLACKJACK_PLAYER;
import static blackjack.model.PlayerFixture.NOT_BLACKJACK_21_PLAYER;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.participant.Dealer;
import blackjack.model.result.ResultCommand;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyStaffTest {

    @Test
    @DisplayName("수익률 목록을 받아 플레이어들의 수익금을 계산한다.")
    void calculatePlayersProfitMoney() {
        BettingRule bettingRule = new BettingRule(new Dealer(UNDER_16_HAND.getHand()));
        MoneyStaff moneyStaff = new MoneyStaff(bettingRule, Map.of(
                BLACKJACK_PLAYER.getPlayer(), new Money(1_000),
                NOT_BLACKJACK_21_PLAYER.getPlayer(), new Money(2_000)));

        assertThat(moneyStaff.calculateProfitMoneys(Map.of(
                BLACKJACK_PLAYER.getPlayer(), ResultCommand.WIN,
                NOT_BLACKJACK_21_PLAYER.getPlayer(), ResultCommand.DRAW)))
                .isEqualTo(Map.of(BLACKJACK_PLAYER.getPlayer(), new Money(1_500),
                        NOT_BLACKJACK_21_PLAYER.getPlayer(), new Money(0)));
    }

    @Test
    @DisplayName("딜러 수익은 모든 플레이어의 수익 합 * (-1)이다.")
    void calculateDealerProfit() {
        BettingRule bettingRule = new BettingRule(new Dealer(UNDER_16_HAND.getHand()));
        MoneyStaff moneyStaff = new MoneyStaff(bettingRule, Map.of(
                BLACKJACK_PLAYER.getPlayer(), new Money(1_000),
                NOT_BLACKJACK_21_PLAYER.getPlayer(), new Money(2_000)));

        assertThat(moneyStaff.calculateDealerProfitAmount(Map.of(BLACKJACK_PLAYER.getPlayer(), new Money(1_500),
                NOT_BLACKJACK_21_PLAYER.getPlayer(), new Money(0)))).isEqualTo(
                new Money(-1_500));
    }
}
