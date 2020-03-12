package blackjack.domain.result;

import blackjack.domain.user.Players;
import blackjack.domain.user.User;

import java.util.Map;

public class Result {
    private final Map<User, WinOrLose> playerResults;

    private Result(User dealer, Players players) {
        playerResults = players.generateResult(dealer);
    }

    public static Result of(User dealer, Players players) {
        return new Result(dealer, players);
    }

    public Map<User, WinOrLose> getPlayerResults() {
        return playerResults;
    }

    public String getDealerResult() {
        StringBuilder stringBuilder = new StringBuilder();

        int all = playerResults.size();

        int dealerLoseCount = (int) playerResults.values().stream()
                .filter(winOrLose -> winOrLose == WinOrLose.WIN)
                .count();
        int dealerWinCount = all - dealerLoseCount;

        stringBuilder.append(dealerWinCount)
                .append("승 ")
                .append(dealerLoseCount)
                .append("패");

        return stringBuilder.toString();
    }
}
