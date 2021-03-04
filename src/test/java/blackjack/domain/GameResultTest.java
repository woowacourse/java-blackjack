package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardValue;
import blackjack.domain.card.Shape;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class GameResultTest {

    @Test
    void name() {
        Player pobi = new Player("pobi");
        Player jason = new Player("jason");
        Player root = new Player("root");
        Dealer dealer = new Dealer();
        Deck deck = new Deck(Arrays.asList(
                Card.valueOf(Shape.DIAMOND, CardValue.TEN),
                Card.valueOf(Shape.SPADE, CardValue.EIGHT),
                Card.valueOf(Shape.DIAMOND, CardValue.ACE),
                Card.valueOf(Shape.HEART, CardValue.EIGHT),
                Card.valueOf(Shape.SPADE, CardValue.TEN),
                Card.valueOf(Shape.CLOVER, CardValue.EIGHT),
                Card.valueOf(Shape.CLOVER, CardValue.TEN),
                Card.valueOf(Shape.SPADE, CardValue.SEVEN)));

        dealer.drawCard(deck);
        dealer.drawCard(deck);

        pobi.drawCard(deck);
        pobi.drawCard(deck);

        jason.drawCard(deck);
        jason.drawCard(deck);

        root.drawCard(deck);
        root.drawCard(deck);

        Players players = new Players(Arrays.asList(pobi, jason, root));
        GameResult gameResult = players.match(dealer);

        Map<ResultType, Integer> expected = new HashMap<>();
        expected.put(ResultType.WIN, 1);
        expected.put(ResultType.TIE, 1);
        expected.put(ResultType.LOSE, 1);

        assertThat(gameResult.getStatistics()).isEqualTo(expected);
    }
}
