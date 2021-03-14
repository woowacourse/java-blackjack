package blackjack.domain.scoreboard;

import blackjack.domain.user.User;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

public class GameResult {
    private final double DEALER_PROFIT_OPERATOR = -1;

    private final Map<User, Double> userResults;

    public GameResult(Map<User, Double> userResults) {
        this.userResults = userResults;
    }

    public Stream<Double> getUserProfit() {
        return userResults.values().stream();
    }

    public Set<Map.Entry<User, Double>> getResultEntrySet() {
        return userResults.entrySet();
    }

    public Set<User> getUserSet() {
        return userResults.keySet();
    }

    public double calculateDealerProfit() {
        return getUserProfit()
                .mapToDouble(i -> i)
                .sum() * DEALER_PROFIT_OPERATOR;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameResult that = (GameResult) o;
        return Objects.equals(userResults, that.userResults);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userResults);
    }
}
