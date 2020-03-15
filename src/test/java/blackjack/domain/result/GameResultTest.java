package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.CardDeck;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
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
        Dealer dealer = new Dealer();
        Players players = new Players("Jamie,Ravie");
        CardDeck cardDeck = new CardDeck();
        dealer.drawCard(new CardDeck(), 2);
        for (Player player : players.getPlayers()) {
            player.drawCard(cardDeck, 2);
        }
        gameResult = new GameResult(dealer, players);
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
        Map<Outcome, Integer> dealerResults = gameResult.getDealerResults();
        int total = 0;
        for (Outcome outcome : dealerResults.keySet()) {
            total += dealerResults.get(outcome);
            System.out.println(outcome + " + " + dealerResults.get(outcome));
        }
        assertThat(total).isEqualTo(2);
    }
}
