package domain.game;

import domain.Name;
import domain.card.Card;
import domain.card.DealerCards;
import domain.card.PlayerCards;
import domain.card.Shape;
import domain.score.ScoreBoard;
import domain.score.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class RuleTest {

    private final Name capy = new Name("capy");
    private DealerCards dealerCards;
    private PlayerCards playerCards;
    private ScoreBoard scoreBoard;
    private Rule rule;

    @BeforeEach
    void setUp() {
        dealerCards = new DealerCards(List.of(
                new Card(6, Shape.CLUB),
                new Card(10, Shape.CLUB)
        ));

        playerCards = new PlayerCards(capy, List.of(
                new Card(10, Shape.CLUB),
                new Card(11, Shape.CLUB)
        ));

        scoreBoard = ScoreBoard.from(List.of(capy));
        rule = new Rule(scoreBoard);
    }

    @Test
    @DisplayName("타겟 카드의 승무패를 결정한다.")
    void decideStatus() {
        Status dealerStatus = rule.decideStatus(dealerCards, playerCards);
        Status playerStatus = rule.decideStatus(playerCards, dealerCards);

        assertAll(
                () -> assertThat(dealerStatus).isEqualTo(Status.LOSE),
                () -> assertThat(playerStatus).isEqualTo(Status.WIN)
        );
    }

    @Test
    @DisplayName("플레이어와 딜러의 승패를 반영한다.")
    void decideResult() {
        rule.decideResult(dealerCards, List.of(playerCards));

        assertAll(
                () -> assertThat(scoreBoard.getDealerScore().getScore(Status.WIN)).isEqualTo(0),
                () -> assertThat(scoreBoard.getDealerScore().getScore(Status.TIE)).isEqualTo(0),
                () -> assertThat(scoreBoard.getDealerScore().getScore(Status.LOSE)).isEqualTo(1),
                () -> assertThat(scoreBoard.getPlayerScore().get(capy)).isEqualTo(Status.WIN)
        );
    }
}
