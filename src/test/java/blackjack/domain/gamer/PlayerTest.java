package blackjack.domain.gamer;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PlayerTest {

    private Hand score14Hand, score21Hand, bustHand, blackjackHand;

    @BeforeEach
    void setUP() {
        score14Hand = new Hand(List.of(
                new Card(CardShape.DIAMOND, CardNumber.KING), new Card(CardShape.SPADE, CardNumber.FOUR)
        ));

        score21Hand = new Hand(List.of(
                new Card(CardShape.CLOVER, CardNumber.KING), new Card(CardShape.SPADE, CardNumber.NINE),
                new Card(CardShape.SPADE, CardNumber.TWO)
        ));

        bustHand = new Hand(List.of(
                new Card(CardShape.HEART, CardNumber.KING), new Card(CardShape.SPADE, CardNumber.JACK),
                new Card(CardShape.SPADE, CardNumber.TWO)
        ));

        blackjackHand = new Hand(List.of(
                new Card(CardShape.SPADE, CardNumber.ACE), new Card(CardShape.SPADE, CardNumber.QUEEN)
        ));
    }

    @Nested
    @DisplayName("카드를 받는 조건 테스트")
    class CanReceiveCardTest {

        @Test
        @DisplayName("카드의 총합이 21 이하이면 카드를 받을 수 있다.")
        void receiveCardTest() {
            Player player = new Player(score14Hand, "hogi");

            assertThat(player.canReceiveCard()).isTrue();
        }

        @Test
        @DisplayName("카드의 총합이 21을 초과하면 카드를 받을 수 없다.")
        void cantReceiveCardTest() {
            Player player = new Player(bustHand, "jazz");

            assertThat(player.canReceiveCard()).isFalse();
        }
    }

    @Nested
    @DisplayName("플레이어가 패배하는 경우의 수 테스트")
    class PlayerLoseTest {

        @Test
        @DisplayName("점수가 21을 초과하면 딜러의 점수와 무관하게 패배한다")
        void playerBustTest() {
            Player player = new Player(bustHand, "ready");
            Score dealerScore = new Score(22, 3);

            GameResult gameResult = player.compete(dealerScore);

            assertThat(gameResult).isEqualTo(GameResult.LOSE);
        }

        @Test
        @DisplayName("플레이어와 딜러의 점수가 모두 21 이하이면서, 딜러의 점수보다 낮으면 패배한다.")
        void playerHandValueLowerThanDealer() {
            Player player = new Player(score14Hand, "jazz");
            Score dealerScore = new Score(21, 3);

            assertThat(player.compete(dealerScore)).isEqualTo(GameResult.LOSE);
        }

        @Test
        @DisplayName("딜러가 블랙잭일 때, 플레이어가 블랙잭이 아니면 패배한다.")
        void playerLoseWhenDealerBlackjack() {
            Player player = new Player(score14Hand, "jazz");
            Score dealerScore = new Score(21, 2);

            assertThat(player.compete(dealerScore)).isEqualTo(GameResult.LOSE);
        }
    }

    @Nested
    @DisplayName("플레이어가 승리하는 경우의 수 테스트")
    class PlayerWinTest {

        @Test
        @DisplayName("플레이어 점수가 21 이하일 때, 딜러의 점수가 21을 초과하면 승리한다.")
        void dealerBustTest() {
            Player player = new Player(score21Hand, "jazz");
            Score dealerScore = new Score(22, 4);

            assertThat(player.compete(dealerScore)).isEqualTo(GameResult.WIN);
        }

        @Test
        @DisplayName("플레이어 점수가 21 이하이고, 딜러의 점수보다 큰 경우 승리한다.")
        void playerHandValueHigherThanDealer() {
            Player player = new Player(score21Hand, "jazz");
            Score dealerScore = new Score(14, 2);

            assertThat(player.compete(dealerScore)).isEqualTo(GameResult.WIN);
        }
    }

    @Nested
    @DisplayName("플레이어가 블랙잭 승리하는 경우의 수 테스트")
    class PlayerBlackjackWinTest {

        @Test
        @DisplayName("플레이어가 블랙잭일 때, 딜러가 블랙잭이 아니면 블랙잭 승리한다.")
        void dealerBustTest() {
            Player player = new Player(blackjackHand, "jazz");
            Score dealerScore21 = new Score(21, 3);
            Score dealerBustHand = new Score(22, 3);

            assertThat(player.compete(dealerScore21)).isEqualTo(GameResult.BLACKJACK_WIN);
            assertThat(player.compete(dealerBustHand)).isEqualTo(GameResult.BLACKJACK_WIN);
        }
    }

    @Nested
    @DisplayName("플레이어 딜러 무승부 테스트")
    class PlayerDealerDrawTest {

        @Test
        @DisplayName("플레이어가 블랙잭일 때, 딜러가 블랙잭이면 무승부.")
        void allBlackjackTest() {
            Player player = new Player(blackjackHand, "jazz");
            Score dealerScore = new Score(21, 2);

            assertThat(player.compete(dealerScore)).isEqualTo(GameResult.DRAW);
        }

        @Test
        @DisplayName("플레이어가 블랙잭일 때, 딜러가 블랙잭이면 무승부.")
        void tieScoreTest() {
            Player player = new Player(score21Hand, "jazz");
            Score dealerScore = new Score(21, 4);

            assertThat(player.compete(dealerScore)).isEqualTo(GameResult.DRAW);
        }
    }
}
