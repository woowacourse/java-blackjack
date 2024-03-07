package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Number;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;
import blackjack.testutil.CustomDeck;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackResultTest {
    @DisplayName("딜러의 승패 결과는 플레이어의 승패 결과를 반대로 반환한다.")
    @Test
    void getDealerResultsTest() {
        Map<Player, HandResult> playerResults = Map.of(generatePlayer(), HandResult.WIN,
                generatePlayer(), HandResult.WIN,
                generatePlayer(), HandResult.DRAW,
                generatePlayer(), HandResult.LOSE);
        BlackjackResult blackjackResult = new BlackjackResult(playerResults);
        Map<HandResult, Integer> dealerResults = blackjackResult.getDealerResult();
        assertThat(dealerResults).contains(Map.entry(HandResult.WIN, 1), Map.entry(HandResult.LOSE, 2), Map.entry(HandResult.DRAW, 1));
    }

    private Player generatePlayer() {
        Deck deck = new CustomDeck(List.of(Number.ACE, Number.EIGHT));
        HandGenerator handGenerator = new HandGenerator(deck);
        return new Player(new Name("gamza"), handGenerator);
    }
}
