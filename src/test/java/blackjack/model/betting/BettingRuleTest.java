package blackjack.model.betting;


import static blackjack.model.PlayerFixture.BLACKJACK_PLAYER;
import static blackjack.model.PlayerFixture.NOT_BLACKJACK_21_PLAYER;
import static blackjack.model.result.ResultCommand.WIN;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.deck.Card;
import blackjack.model.deck.Score;
import blackjack.model.deck.Shape;
import blackjack.model.participant.Dealer;
import blackjack.model.participant.Hand;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingRuleTest {

    @Test
    @DisplayName("플레이어가 BlackJack으로 승리한 경우, 배팅 금액의 1.5배를 준다.")
    void findProfitAmountWhenPlayerWinByBlackJack() {
        BettingRule bettingRule = new BettingRule(new Dealer(new Hand(List.of(new Card(Shape.SPADE, Score.ACE), new Card(Shape.HEART, Score.TWO)))));
        assertThat(bettingRule.calculateProfitRate(BLACKJACK_PLAYER.getPlayer(), WIN)).isEqualTo(1.5);
    }

    @Test
    @DisplayName("딜러가 버스트여서 플레이어가 승리한 경우, 배팅 금액의 0배를 준다. (= 추가 수익이 없다.)")
    void findProfitAmountWhenPlayerWinByDealerBust() {
        BettingRule bettingRule = new BettingRule(new Dealer(new Hand(List.of(new Card(Shape.SPADE, Score.TEN), new Card(Shape.HEART, Score.TEN), new Card(Shape.DIA, Score.TEN)))));
        assertThat(bettingRule.calculateProfitRate(NOT_BLACKJACK_21_PLAYER.getPlayer(), WIN)).isEqualTo(0);
    }
}
