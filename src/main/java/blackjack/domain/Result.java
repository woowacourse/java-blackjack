package blackjack.domain;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static blackjack.domain.Round.GAME_OVER_SCORE;

public class Result {
    private final List<Outcome> dealerOutcomes;
    private final Map<String, Outcome> playerResults;

    public Result(Dealer dealer, List<Player> players) {
        dealerOutcomes = players.stream()
                .map(player -> Outcome.findOutcome(dealer.calculateScore(GAME_OVER_SCORE), player.calculateScore(GAME_OVER_SCORE)))
                .collect(Collectors.toList());

        int playerCount = dealerOutcomes.size();
        playerResults = new LinkedHashMap<>();
        for (int i = 0; i < playerCount; i++) {
            playerResults.put(players.get(i).getName(), Outcome.reverseResult(dealerOutcomes.get(i)));
        }
    }

    public List<Outcome> getDealerOutcomes() {
        return dealerOutcomes;
    }

    public Map<String, Outcome> getPlayerResults() {
        return playerResults;
    }
}
