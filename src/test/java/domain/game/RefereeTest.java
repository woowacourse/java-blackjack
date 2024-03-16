package domain.game;

import domain.card.Card;
import domain.card.DealerCards;
import domain.card.PlayerCards;
import domain.card.Shape;
import domain.player.Name;
import domain.score.Referee;
import domain.score.Revenue;
import domain.score.ScoreBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@Nested
class RefereeTest {

    private final Name capy = new Name("capy");
    private PlayerCards playerCards;
    private ScoreBoard scoreBoard = new ScoreBoard(Map.of(capy, new Bet(20000)));

    @Nested
    @DisplayName("딜러가 블랙잭이 아니다.")
    class DealerNotBlackjackTest {

        private final DealerCards dealerCards = new DealerCards(List.of(
                new Card(9, Shape.CLUB),
                new Card(10, Shape.SPADE)
        ));
        private final Referee referee = new Referee(scoreBoard);

        @Test
        @DisplayName("플레이어만 블랙잭이면 플레이어의 수익은 베팅금액의 1.5배이다")
        void decideResult_PlayerBlackjack_OneAndHalfTimes() {
            playerCards = new PlayerCards(List.of(
                    new Card(1, Shape.HEART),
                    new Card(10, Shape.DIAMONDS)
            ));

            referee.decideResult(dealerCards, Map.of(capy, playerCards));

            Revenue capyRevenue = scoreBoard.getPlayersRevenues().get(capy);
            assertThat(capyRevenue).isEqualTo(new Revenue(30000));
        }

        @Test
        @DisplayName("플레이어 카드의 합이 딜러 카드의 합보다 크다면 수익은 베팅금액이다.")
        void decideResult_PlayerWin_PlusBet() {
            playerCards = new PlayerCards(List.of(
                    new Card(10, Shape.HEART),
                    new Card(10, Shape.DIAMONDS)
            ));

            referee.decideResult(dealerCards, Map.of(capy, playerCards));

            Revenue capyRevenue = scoreBoard.getPlayersRevenues().get(capy);
            assertThat(capyRevenue).isEqualTo(new Revenue(20000));
        }

        @Test
        @DisplayName("플레이어 카드 합과 딜러 카드 합이 같다면 수익은 0이다.")
        void decideResult_Tie_Zero() {
            playerCards = new PlayerCards(List.of(
                    new Card(9, Shape.HEART),
                    new Card(10, Shape.DIAMONDS)
            ));

            referee.decideResult(dealerCards, Map.of(capy, playerCards));

            Revenue capyRevenue = scoreBoard.getPlayersRevenues().get(capy);
            assertThat(capyRevenue).isEqualTo(new Revenue(0));
        }

        @Test
        @DisplayName("플레이어 카드 합이 딜러 카드 합보다 작다면 베팅금액을 잃는다.")
        void decideResult_PlayerLose_MinusBet() {
            playerCards = new PlayerCards(List.of(
                    new Card(8, Shape.HEART),
                    new Card(10, Shape.DIAMONDS)
            ));

            referee.decideResult(dealerCards, Map.of(capy, playerCards));

            Revenue capyRevenue = scoreBoard.getPlayersRevenues().get(capy);
            assertThat(capyRevenue).isEqualTo(new Revenue(-20000));
        }


        @Test
        @DisplayName("플레이어 카드 합이 21을 초과하면 플레이어는 베팅금액을 잃는다.")
        void decideResult_PlayerBust_MinusBet() {
            playerCards = new PlayerCards(List.of(
                    new Card(8, Shape.HEART),
                    new Card(10, Shape.DIAMONDS)
            ));
            playerCards.receive(new Card(4, Shape.CLUB));

            referee.decideResult(dealerCards, Map.of(capy, playerCards));

            Revenue capyRevenue = scoreBoard.getPlayersRevenues().get(capy);
            assertThat(capyRevenue).isEqualTo(new Revenue(-20000));
        }

        @Test
        @DisplayName("플레이어가 블랙잭이면서 21을 초과한 경우는 베팅금액을 잃는다.")
        void decideResult_BlackjackAndBust_MinusBet() {
            PlayerCards playerCards = new PlayerCards(List.of(
                    new Card(1, Shape.HEART),
                    new Card(10, Shape.CLUB)
            ));
            playerCards.receive(new Card(5, Shape.SPADE));
            playerCards.receive(new Card(6, Shape.SPADE));

            referee.decideResult(dealerCards, Map.of(capy, playerCards));

            Revenue capyRevenue = scoreBoard.getPlayersRevenues().get(capy);
            assertThat(capyRevenue).isEqualTo(new Revenue(-20000));
        }
    }

    @Nested
    @DisplayName("딜러가 블랙잭이다.")
    class DealerBlackjackTest {
        private final DealerCards dealerCards = new DealerCards(List.of(
                new Card(1, Shape.CLUB),
                new Card(10, Shape.SPADE)
        ));
        private final Referee referee = new Referee(scoreBoard);

        @Test
        @DisplayName("딜러와 플레이어가 모두 블랙잭이라면 수익은 0이다.")
        void decideResult_BothBlackJack_Zero() {
            playerCards = new PlayerCards(List.of(
                    new Card(1, Shape.HEART),
                    new Card(10, Shape.DIAMONDS)
            ));

            referee.decideResult(dealerCards, Map.of(capy, playerCards));

            Revenue capyRevenue = scoreBoard.getPlayersRevenues().get(capy);
            assertThat(capyRevenue).isEqualTo(new Revenue(0));
        }

        @Test
        @DisplayName("플레이어 카드 합이 딜러와 같으면 수익은 0이다.")
        void decideResult_SameDealer_Zero() {
            PlayerCards playerCards = new PlayerCards(List.of(
                    new Card(9, Shape.HEART),
                    new Card(10, Shape.CLUB)
            ));
            playerCards.receive(new Card(2, Shape.SPADE));

            referee.decideResult(dealerCards, Map.of(capy, playerCards));

            Revenue capyRevenue = scoreBoard.getPlayersRevenues().get(capy);
            assertThat(capyRevenue).isEqualTo(new Revenue(0));
        }
    }

    @Nested
    @DisplayName("딜러 카드의 합이 21을 초과한다.")
    class DealerBustTest {

        private final DealerCards dealerCards = new DealerCards(List.of(
                new Card(9, Shape.HEART),
                new Card(10, Shape.CLUB)
        ));
        private final Referee referee = new Referee(scoreBoard);

        @BeforeEach
        void setUp() {
            dealerCards.receive(new Card(3, Shape.SPADE));
        }

        @Test
        @DisplayName("플레이어 카드 합이 21을 초과하지 않으면 수익은 베팅금액이다.")
        void decideResult_LessThan21_PlusBet() {
            PlayerCards playerCards = new PlayerCards(List.of(
                    new Card(9, Shape.HEART),
                    new Card(10, Shape.CLUB)
            ));

            referee.decideResult(dealerCards, Map.of(capy, playerCards));

            Revenue capyRevenue = scoreBoard.getPlayersRevenues().get(capy);
            assertThat(capyRevenue).isEqualTo(new Revenue(20000));
        }

        @Test
        @DisplayName("플레이어 카드 합이 21을 초과하면 베팅금액을 잃는다.")
        void decideResult_Over21_MinusBet() {
            PlayerCards playerCards = new PlayerCards(List.of(
                    new Card(9, Shape.HEART),
                    new Card(10, Shape.CLUB)
            ));
            playerCards.receive(new Card(3, Shape.SPADE));

            referee.decideResult(dealerCards, Map.of(capy, playerCards));

            Revenue capyRevenue = scoreBoard.getPlayersRevenues().get(capy);
            assertThat(capyRevenue).isEqualTo(new Revenue(-20000));
        }
    }
}
