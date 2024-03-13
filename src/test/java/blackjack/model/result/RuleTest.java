package blackjack.model.result;

import static blackjack.model.deck.Score.ACE;
import static blackjack.model.deck.Score.TEN;
import static blackjack.model.deck.Shape.DIA;
import static blackjack.model.deck.Shape.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.deck.Card;
import blackjack.model.deck.Deck;
import blackjack.model.participant.Dealer;
import blackjack.model.participant.Player;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RuleTest {

    @Test
    @DisplayName("딜러와 비교하여 결과를 계산한다.")
    void calculateResult() {
        Dealer dealer = new Dealer(new Deck());
        dealer.receiveInitialCards(List.of(new Card(DIA, TEN), new Card(SPADE, TEN)));
        Player player = new Player("리브");
        player.receiveInitialCards(List.of(new Card(DIA, ACE), new Card(SPADE, TEN)));
        Rule rule = new Rule(dealer);

        assertThat(rule.calculateResult(player)).isEqualTo(ResultCommand.WIN);
    }

}
