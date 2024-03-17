package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Number;
import blackjack.domain.card.Shape;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Money;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BlackjackEarningCalculatorTest {

    @Nested
    @DisplayName("딜러의 수익을 계산한다.")
    class DealerEarningTest {

        BlackjackEarningCalculator calculator;

        @BeforeEach
        void setUp() {
            Dealer dealer = new Dealer(List.of(
                    new Card(Shape.SPADE, Number.NINE),
                    new Card(Shape.HEART, Number.SIX),
                    new Card(Shape.DIAMOND, Number.SIX)
            ));
            calculator = BlackjackEarningCalculator.fromDealer(dealer);
        }

        @Test
        @DisplayName("딜러는 패배한 플레이어의 베팅 금액만큼 수익을 얻는다.")
        void calculateDealerEarning_WhenPlayerWin() {
            Player winPlayer = new Player("win", List.of(
                    new Card(Shape.DIAMOND, Number.ACE),
                    new Card(Shape.SPADE, Number.KING)
            ));
            Player drawPlayer = new Player("draw", List.of(
                    new Card(Shape.SPADE, Number.EIGHT),
                    new Card(Shape.DIAMOND, Number.SEVEN),
                    new Card(Shape.HEART, Number.SIX)
            ));
            Player losePlayer = new Player("lose", List.of(
                    new Card(Shape.HEART, Number.QUEEN),
                    new Card(Shape.HEART, Number.KING)
            ));

            Players players = new Players(Map.of(
                    winPlayer, new Money(10000L),
                    drawPlayer, new Money(20000L),
                    losePlayer, new Money(30000L)
            ));

            assertThat(calculator.calculateDealerEarning(players))
                    .isEqualTo(new Money(30000L));
        }
    }

    @Nested
    @DisplayName("플레이어의 결과를 바탕으로 수익을 계산한다.")
    class PlayerEarningTest {

        BlackjackEarningCalculator calculator;
        Player player;
        Money betAmount;

        @BeforeEach
        void setUp() {
            Dealer dealer = new Dealer(List.of(
                    new Card(Shape.HEART, Number.QUEEN),
                    new Card(Shape.DIAMOND, Number.QUEEN)
            ));
            calculator = BlackjackEarningCalculator.fromDealer(dealer);
            betAmount = new Money(10000L);
        }

        @Test
        @DisplayName("블랙잭 승리인 경우의 수익금은 베팅 금액의 1.5배이다.")
        void calculatePlayerEarning_WhenBlackjackWin() {
            player = createTestPlayer(List.of(
                    new Card(Shape.SPADE, Number.ACE),
                    new Card(Shape.DIAMOND, Number.QUEEN)
            ));

            assertThat(calculator.calculatePlayerEarning(player, betAmount)).isEqualTo(new Money(15000L));
        }

        @Test
        @DisplayName("블랙잭이 아닌 승리인 경우의 수익금은 베팅 금액만큼이다.")
        void calculatePlayerEarning_WhenNotBlackjackWin() {
            player = createTestPlayer(List.of(
                    new Card(Shape.SPADE, Number.EIGHT),
                    new Card(Shape.DIAMOND, Number.SEVEN),
                    new Card(Shape.CLOVER, Number.SIX)
            ));

            assertThat(calculator.calculatePlayerEarning(player, betAmount)).isEqualTo(new Money(10000L));
        }

        @Test
        @DisplayName("무승부인 경우의 수익금은 0원이다.")
        void calculatePlayerEarning_WhenDraw() {
            player = createTestPlayer(List.of(
                    new Card(Shape.SPADE, Number.JACK),
                    new Card(Shape.DIAMOND, Number.TEN)
            ));

            assertThat(calculator.calculatePlayerEarning(player, betAmount)).isEqualTo(Money.getZeroAmountMoney());
        }

        @Test
        @DisplayName("패배한 경우 베팅 금액만큼의 손실이 발생한다.")
        void calculatePlayerEarning_WhenLose() {
            player = createTestPlayer(List.of(
                    new Card(Shape.SPADE, Number.JACK),
                    new Card(Shape.DIAMOND, Number.NINE)
            ));

            assertThat(calculator.calculatePlayerEarning(player, betAmount)).isEqualTo(new Money(-10000L));
        }

        private Player createTestPlayer(List<Card> cards) {
            return new Player("test", cards);
        }
    }
}
