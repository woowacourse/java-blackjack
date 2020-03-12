package blackjack.domain;

import blackjack.domain.user.Players;
import blackjack.domain.user.User;

import java.util.Map;

public class Result {
    private final Map<User, Boolean> playerResults;

    private Result(User dealer, Players players) {
        playerResults = players.generateResult(dealer);
    }

    public static Result of(User dealer, Players players) {
        return new Result(dealer, players);
    }

    public boolean isWinner(User player) {
        return playerResults.get(player);
    }

    public Map<User, Boolean> getPlayerResults() {
        return playerResults;
    }

    public String getDealerResult() {
        StringBuilder stringBuilder = new StringBuilder();

        int all = playerResults.size();

        int dealerLoseCount = (int) playerResults.values().stream()
                .filter(isWinner -> isWinner)
                .count();
        int dealerWinCount = all - dealerLoseCount;

        stringBuilder.append(dealerWinCount)
                .append("승 ")
                .append(dealerLoseCount)
                .append("패");

        return stringBuilder.toString();
    }
}
