package blackjack.domain;

import blackjack.domain.user.Users;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Result {
    private final List<Outcome> dealerOutcomes;
    private final Map<String, Outcome> playerResults;

    public Result(Users users, int gameOverScore) {
        dealerOutcomes = users.getPlayers().stream()
                .map(player ->
                        Outcome.findOutcome(users.getDealer().calculateScore(gameOverScore), player.calculateScore(gameOverScore)))
                .collect(Collectors.toList());

        int playerCount = dealerOutcomes.size();
        playerResults = new LinkedHashMap<>();
        for (int i = 0; i < playerCount; i++) {
            playerResults.put(users.getPlayers().get(i).getName(), Outcome.reverseResult(dealerOutcomes.get(i)));
        }
    }

    public Map<String, Outcome> getPlayerResults() {
        return playerResults;
    }

    public int findDealerWinCount() {
        return (int) dealerOutcomes.stream()
                .filter(Outcome::isWin)
                .count();
    }

    public int findDealerLoseCount() {
        return (int) dealerOutcomes.stream()
                .filter(Outcome::isLose)
                .count();
    }

    public int findDealerDrawCount() {
        return (int) dealerOutcomes.stream()
                .filter(Outcome::isDraw)
                .count();
    }
}
