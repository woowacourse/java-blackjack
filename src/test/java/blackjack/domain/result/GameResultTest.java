package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.CardDeck;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Participants;
import blackjack.domain.user.Player;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameResultTest {

    private static GameResult gameResult;

    @BeforeAll
    static void resetVariable() {
        List<String> playerNames = Arrays.asList("Jamie,Ravie".split(","));
        Participants participants = new Participants(playerNames);
        CardDeck cardDeck = new CardDeck();
        Dealer dealer = participants.getDealer();
        dealer.drawCard(new CardDeck(), 2);
        for (Player player : participants.getPlayers()) {
            player.drawCard(cardDeck, 2);
        }
        gameResult = new GameResult(participants);
    }

    @DisplayName("플레이어의 승무패를 가져옴")
    @Test
    void checkPlayersResult() {
        Map<Player, Outcome> playerResult = gameResult.getPlayersResult();
        List<Outcome> outcomes = Arrays.asList(Outcome.values());

        for (Player player : playerResult.keySet()) {
            assertThat(outcomes.contains(playerResult.get(player))).isTrue();
        }
    }

    @DisplayName("딜러의 승무패를 가져옴")
    @Test
    void checkDealerResult() {
        Map<Outcome, Integer> dealerResults = gameResult.getDealerResultsNoZero();
        int total = 0;
        for (Outcome outcome : dealerResults.keySet()) {
            total += dealerResults.get(outcome);
            assertThat(dealerResults.get(outcome) != 0).isTrue();
        }
        assertThat(total).isEqualTo(2);
    }
}
