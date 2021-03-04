package blackjack.domain;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static blackjack.domain.Round.GAME_OVER_SCORE;

public class Result {
    private static String dealerName;
    private static List<Outcome> dealerOutcomes;
    private static Map<String, Outcome> playerResults;
    private static final Map<String, List<Outcome>> results = new LinkedHashMap<>();

    public Result(Dealer dealer, List<Player> players) {
        dealerName = dealer.getName();
        dealerOutcomes = players.stream()
                .map(player -> Outcome.findOutcome(dealer.calculateScore(GAME_OVER_SCORE), player.calculateScore(GAME_OVER_SCORE)))
                .collect(Collectors.toList());

        int playerCount = dealerOutcomes.size();
        playerResults = new LinkedHashMap<>();
        for (int i = 0; i < playerCount; i++) {
            playerResults.put(players.get(i).getName(), Outcome.reverseResult(dealerOutcomes.get(i)));
        }
    }

    public String getDealerName() {
        return dealerName;
    }

    public List<Outcome> getDealerOutcomes() {
        return dealerOutcomes;
    }

    public Map<String, Outcome> getPlayerResults() {
        return playerResults;
    }
}
