package blackjack.model.result;

import static blackjack.model.deck.Score.ACE;
import static blackjack.model.deck.Score.TEN;
import static blackjack.model.deck.Shape.DIA;
import static blackjack.model.deck.Shape.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.deck.Card;
import blackjack.model.participant.Dealer;
import blackjack.model.participant.Hand;
import blackjack.model.participant.Player;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RuleTest {

    @Test
    @DisplayName("딜러와 비교하여 결과를 계산한다.")
    void calculateResult() {
        Rule rule = new Rule(
                new Dealer(new Hand(List.of(new Card(DIA, TEN), new Card(SPADE, TEN)))));
        Player player = new Player("리브",
                new Hand(List.of(new Card(DIA, ACE), new Card(SPADE, TEN))));
        assertThat(rule.calculateResult(player)).isEqualTo(ResultCommand.WIN);
    }

}
