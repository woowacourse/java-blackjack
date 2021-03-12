package blackjack.dto;

import blackjack.domain.participants.Dealer;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class GameResult {

    private final Map<String, Integer> results;

    public GameResult(Map<String, Integer> playerResults) {
        this.results = new LinkedHashMap<>();
        results.put(Dealer.DEALER_NAME, getDealerProfit(playerResults));
        results.putAll(playerResults);
    }

    private static int getDealerProfit(Map<String, Integer> playersResults) {
        return (-1) * playersResults.values().stream().reduce(0, Integer::sum);
    }

    public Map<String, Integer> unwrap() {
        return new LinkedHashMap<>(results);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GameResult that = (GameResult) o;
        return Objects.equals(results, that.results);
    }

    @Override
    public int hashCode() {
        return Objects.hash(results);
    }
}
