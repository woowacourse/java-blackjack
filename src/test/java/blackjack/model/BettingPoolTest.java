package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.deck.Card;
import blackjack.model.deck.Score;
import blackjack.model.deck.Shape;
import blackjack.model.participant.BettingAmount;
import blackjack.model.participant.Hand;
import blackjack.model.participant.Player;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingPoolTest {

    @Test
    @DisplayName("플레이어가 만약 블랙잭으로 승리한 경우, 베팅 금액의 1.5배를 딜러에게 받는다.")
    void winByBlackJack() {
        Player player = Player.of("몰리", new Hand(List.of(new Card(Shape.DIA, Score.TEN), new Card(Shape.DIA, Score.ACE))));
        BettingAmount bettingAmount = new BettingAmount(1000);
        Map<Player, BettingAmount> moneyByPlayer = Map.of(player, bettingAmount);

        BettingPool bettingPool = new BettingPool(moneyByPlayer);
        assertThat(bettingPool.giveBlackJackMoney(player)).isEqualTo(bettingAmount.multiple(1.5));
    }
}
