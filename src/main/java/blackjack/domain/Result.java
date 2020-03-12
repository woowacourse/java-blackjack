package blackjack.domain;

import blackjack.domain.user.Player;
import blackjack.domain.user.Players;

import java.util.Map;

public class Result {
    private final Map<Player, Boolean> playerResults;

    private Result(Player dealer, Players players) {
        playerResults = players.generateResult(dealer);
    }

    public static Result of(Player dealer, Players players) {
        return new Result(dealer, players);
    }

    public boolean isWinner(Player player) {
        return playerResults.get(player);
    }

    public Map<Player, Boolean> getPlayerResults() {
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
