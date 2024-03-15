package blackjack.model.betting;

import static blackjack.model.PlayerFixture.BLACKJACK_PLAYER;
import static blackjack.model.PlayerFixture.NOT_BLACKJACK_21_PLAYER;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.deck.Card;
import blackjack.model.deck.Score;
import blackjack.model.deck.Shape;
import blackjack.model.participant.Dealer;
import blackjack.model.participant.Hand;
import blackjack.model.result.ResultCommand;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyStaffTest {

    @Test
    @DisplayName("수익률 목록을 받아 플레이어들의 수익금을 계산한다.")
    void calculatePlayersProfitMoney() {
        BettingRule bettingRule = new BettingRule(new Dealer(new Hand(List.of(new Card(Shape.DIA, Score.TWO), new Card(Shape.SPADE, Score.TWO)))));
        MoneyStaff moneyStaff = new MoneyStaff(bettingRule, Map.of(
                BLACKJACK_PLAYER.getPlayer(), new Money(1_000),
                NOT_BLACKJACK_21_PLAYER.getPlayer(), new Money(2_000)));

        assertThat(moneyStaff.calculateProfitMoneys(Map.of(
                BLACKJACK_PLAYER.getPlayer(), ResultCommand.WIN,
                NOT_BLACKJACK_21_PLAYER.getPlayer(), ResultCommand.DRAW))).isEqualTo(List.of(new Money(1_500), new Money(0)));
    }

    @Test
    @DisplayName("딜러 수익은 모든 플레이어의 수익 합 * (-1)이다.")
    void calculateDealerProfit() {
        BettingRule bettingRule = new BettingRule(new Dealer(new Hand(List.of(new Card(Shape.DIA, Score.TWO), new Card(Shape.SPADE, Score.TWO)))));
        MoneyStaff moneyStaff = new MoneyStaff(bettingRule, Map.of(
                BLACKJACK_PLAYER.getPlayer(), new Money(1_000),
                NOT_BLACKJACK_21_PLAYER.getPlayer(), new Money(2_000)));

        assertThat(moneyStaff.calculateDealerProfitAmount(List.of(new Money(1_500), new Money(0)))).isEqualTo(new Money(-1_500));
    }
}
