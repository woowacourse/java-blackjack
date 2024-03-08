package domain;

import domain.card.Card;
import domain.card.DealerCards;
import domain.card.PlayerCards;
import domain.card.Shape;
import domain.game.Rule;
import domain.score.ScoreBoard;
import domain.score.Status;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class RuleTest {

    @Test
    @DisplayName("타겟 카드의 승무패를 결정한다.")
    void decideStatus() {
        DealerCards dealerCards = new DealerCards(List.of(new Card(10, Shape.CLUB), new Card(6, Shape.CLUB)));

        Name capy = new Name("capy");
        PlayerCards playerCards = new PlayerCards(capy, List.of(new Card(10, Shape.CLUB), new Card(11, Shape.CLUB)));

        Rule rule = new Rule(ScoreBoard.from(List.of(capy)));
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
        DealerCards dealerCards = new DealerCards(List.of(new Card(10, Shape.CLUB), new Card(6, Shape.CLUB)));

        Name capy = new Name("capy");
        PlayerCards playerCards = new PlayerCards(capy, List.of(new Card(10, Shape.CLUB), new Card(11, Shape.CLUB)));

        ScoreBoard scoreBoard = ScoreBoard.from(List.of(capy));
        new Rule(scoreBoard).decideResult(dealerCards, List.of(playerCards));

        assertAll(
                () -> assertThat(scoreBoard.getDealerScore().getWinScore()).isEqualTo(0),
                () -> assertThat(scoreBoard.getDealerScore().getTieScore()).isEqualTo(0),
                () -> assertThat(scoreBoard.getDealerScore().getLoseScore()).isEqualTo(1),
                () -> assertThat(scoreBoard.getPlayerScore().get(capy)).isEqualTo(Status.WIN)
        );
    }
}
