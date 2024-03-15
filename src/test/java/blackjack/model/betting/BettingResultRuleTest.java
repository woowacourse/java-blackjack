package blackjack.model.betting;


import static blackjack.model.PlayerFixture.BLACKJACK_PLAYER;
import static blackjack.model.PlayerFixture.NOT_BLACKJACK_21_PLAYER;
import static blackjack.model.result.ResultCommand.DRAW;
import static blackjack.model.result.ResultCommand.LOSE;
import static blackjack.model.result.ResultCommand.WIN;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.deck.Card;
import blackjack.model.deck.Score;
import blackjack.model.deck.Shape;
import blackjack.model.participant.Dealer;
import blackjack.model.participant.Hand;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BettingResultRuleTest {

    @Nested
    class whenPlayerWin {

        @Test
        @DisplayName("플레이어가 BlackJack으로 승리한 경우, 배팅 금액의 1.5배를 준다.")
        void calculateProfitRateByBlackJack() {
            BettingRule bettingRule = new BettingRule(
                    new Dealer(new Hand(List.of(new Card(Shape.SPADE, Score.ACE), new Card(Shape.HEART, Score.TWO)))));
            assertThat(bettingRule.calculateProfitRate(BLACKJACK_PLAYER.getPlayer(), WIN)).isEqualTo(1.5);
        }

        @Test
        @DisplayName("딜러가 버스트여서 플레이어가 승리한 경우, 배팅 금액의 0배를 준다. (= 추가 수익이 없다.)")
        void calculateProfitRateByDealerBust() {
            BettingRule bettingRule = new BettingRule(new Dealer(new Hand(
                    List.of(new Card(Shape.SPADE, Score.TEN), new Card(Shape.HEART, Score.TEN),
                            new Card(Shape.DIA, Score.TEN)))));
            assertThat(bettingRule.calculateProfitRate(NOT_BLACKJACK_21_PLAYER.getPlayer(), WIN)).isEqualTo(0);
        }

        @Test
        @DisplayName("딜러가 버스트가 아닌데도 플레이어가 승리한 경우, 배팅 금액의 1배를 준다.")
        void calculateProfitRateByDealerNotBust() {
            BettingRule bettingRule = new BettingRule(
                    new Dealer(new Hand(List.of(new Card(Shape.SPADE, Score.TWO), new Card(Shape.HEART, Score.TWO)))));
            assertThat(bettingRule.calculateProfitRate(NOT_BLACKJACK_21_PLAYER.getPlayer(), WIN)).isEqualTo(1);
        }
    }

    @Test
    @DisplayName("플레이어가 무승부인 경우, 배팅 금액의 0배를 준다.")
    void calculateProfitRateWhenDraw() {
        BettingRule bettingRule = new BettingRule(
                new Dealer(new Hand(List.of(new Card(Shape.SPADE, Score.TWO), new Card(Shape.HEART, Score.TWO)))));
        assertThat(bettingRule.calculateProfitRate(NOT_BLACKJACK_21_PLAYER.getPlayer(), DRAW)).isEqualTo(0);
    }

    @Test
    @DisplayName("플레이어가 지는 경우, 베팅 금액만큼을 잃는다.")
    void calculateProfitRateWhenPlayerLose() {
        BettingRule bettingRule = new BettingRule(
                new Dealer(new Hand(List.of(new Card(Shape.SPADE, Score.ACE), new Card(Shape.HEART, Score.TEN)))));
        assertThat(bettingRule.calculateProfitRate(NOT_BLACKJACK_21_PLAYER.getPlayer(), LOSE)).isEqualTo(-1);
    }
}
