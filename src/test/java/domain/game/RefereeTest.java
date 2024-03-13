package domain.game;

import domain.card.Card;
import domain.card.DealerCards;
import domain.card.PlayerCards;
import domain.card.Shape;
import domain.player.Bet;
import domain.player.Name;
import domain.score.Revenue;
import domain.score.ScoreBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class RefereeTest {

    private final Name capy = new Name("capy");
    private DealerCards dealerCards;
    private PlayerCards playerCards;
    private Referee referee;
    private ScoreBoard scoreBoard;

    @BeforeEach
    void setUp() {
        scoreBoard = new ScoreBoard(Map.of(capy, new Bet(20000)));
        referee = new Referee(scoreBoard);
        dealerCards = new DealerCards(List.of(
                new Card(9, Shape.CLUB),
                new Card(10, Shape.SPADE)
        ));
    }

    @Test
    @DisplayName("플레이어만 블랙잭이면 플레이어의 수익은 베팅금액의 1.5배이다")
    void decideResult_PlayerBlackjack_OneAndHalfTimes() {
        playerCards = new PlayerCards(capy, List.of(
                new Card(1, Shape.HEART),
                new Card(10, Shape.DIAMONDS)
        ));

        referee.decideResult(dealerCards, List.of(playerCards));

        Revenue capyRevenue = scoreBoard.getPlayersRevenues().get(capy);
        assertThat(capyRevenue).isEqualTo(new Revenue(30000));
    }

    @Test
    @DisplayName("딜러만 21을 초과하면 플레이어의 수익은 베팅금액이다.")
    void decideResult_Dealer_MinusBet() {
        dealerCards = new DealerCards(List.of(
                new Card(6, Shape.CLUB),
                new Card(10, Shape.SPADE)
        ));
        dealerCards.receive(new Card(6, Shape.CLUB));

        playerCards = new PlayerCards(capy, List.of(
                new Card(8, Shape.HEART),
                new Card(10, Shape.DIAMONDS)
        ));

        referee.decideResult(dealerCards, List.of(playerCards));

        Revenue capyRevenue = scoreBoard.getPlayersRevenues().get(capy);
        assertThat(capyRevenue).isEqualTo(new Revenue(20000));
    }

    @Test
    @DisplayName("플레이어가 승리하면 플레이어의 수익은 베팅금액이다.")
    void decideResult_PlayerWin_PlusBet() {
        playerCards = new PlayerCards(capy, List.of(
                new Card(10, Shape.HEART),
                new Card(10, Shape.DIAMONDS)
        ));

        referee.decideResult(dealerCards, List.of(playerCards));

        Revenue capyRevenue = scoreBoard.getPlayersRevenues().get(capy);
        assertThat(capyRevenue).isEqualTo(new Revenue(20000));
    }

    @Test
    @DisplayName("플레이어와 딜러가 모두 블랙잭이 아니면서 무승부라면 플레이어의 수익은 0이다.")
    void decideResult_Tie_Zero() {
        playerCards = new PlayerCards(capy, List.of(
                new Card(9, Shape.HEART),
                new Card(10, Shape.DIAMONDS)
        ));

        referee.decideResult(dealerCards, List.of(playerCards));

        Revenue capyRevenue = scoreBoard.getPlayersRevenues().get(capy);
        assertThat(capyRevenue).isEqualTo(new Revenue(0));
    }

    @Test
    @DisplayName("딜러와 플레이어가 모두 블랙잭이라면 딜러와 플레이어의 수익은 0이다.")
    void decideResult_BothBlackJack_Zero() {
        dealerCards = new DealerCards(List.of(
                new Card(1, Shape.CLUB),
                new Card(10, Shape.SPADE)
        ));

        playerCards = new PlayerCards(capy, List.of(
                new Card(1, Shape.HEART),
                new Card(10, Shape.DIAMONDS)
        ));

        referee.decideResult(dealerCards, List.of(playerCards));

        Revenue capyRevenue = scoreBoard.getPlayersRevenues().get(capy);
        assertThat(capyRevenue).isEqualTo(new Revenue(0));
    }

    @Test
    @DisplayName("플레이어가 패배하면 플레이어는 베팅금액을 잃는다.")
    void decideResult_PlayerLose_MinusBet() {
        playerCards = new PlayerCards(capy, List.of(
                new Card(8, Shape.HEART),
                new Card(10, Shape.DIAMONDS)
        ));

        referee.decideResult(dealerCards, List.of(playerCards));

        Revenue capyRevenue = scoreBoard.getPlayersRevenues().get(capy);
        assertThat(capyRevenue).isEqualTo(new Revenue(-20000));
    }

    @Test
    @DisplayName("플레이어가 패배하면 플레이어는 베팅금액을 잃는다.")
    void decideResult_PlayerBust_MinusBet() {
        playerCards = new PlayerCards(capy, List.of(
                new Card(8, Shape.HEART),
                new Card(10, Shape.DIAMONDS)
        ));
        playerCards.receive(new Card(4, Shape.CLUB));

        referee.decideResult(dealerCards, List.of(playerCards));

        Revenue capyRevenue = scoreBoard.getPlayersRevenues().get(capy);
        assertThat(capyRevenue).isEqualTo(new Revenue(-20000));
    }
}
