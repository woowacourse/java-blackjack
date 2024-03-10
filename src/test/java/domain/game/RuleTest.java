package domain.game;

import domain.card.Card;
import domain.card.DealerCards;
import domain.card.PlayerCards;
import domain.card.Shape;
import domain.player.Name;
import domain.score.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class RuleTest {

    private DealerCards dealerCards;
    private PlayerCards playerCards;
    private Rule rule;

    @BeforeEach
    void setUp() {
        Name capy = new Name("capy");

        dealerCards = new DealerCards(List.of(
                new Card(6, Shape.CLUB),
                new Card(10, Shape.CLUB)
        ));

        playerCards = new PlayerCards(capy, List.of(
                new Card(10, Shape.CLUB),
                new Card(11, Shape.CLUB)
        ));

        rule = new Rule();
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
}
