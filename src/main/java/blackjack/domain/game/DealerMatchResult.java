package blackjack.domain.game;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.Collection;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class DealerMatchResult {
    private final Map<ResultType, ResultCount> matchResult;
    private final String name;

    private DealerMatchResult(Map<ResultType, ResultCount> matchResult, String name) {
        this.matchResult = matchResult;
        this.name = name;
    }

    public static DealerMatchResult of(Dealer dealer, List<Player> players) {
        return new DealerMatchResult(generateDealerResult(dealer, players), dealer.getName());
    }

    private static Map<ResultType, ResultCount> generateDealerResult(Dealer dealer, Collection<Player> players) {
        Map<ResultType, ResultCount> results = generateDefaultResults();

        for (Player player : players) {
            ResultType result = dealer.compareWith(player);
            results.get(result).increment();
        }

        return results;
    }

    private static Map<ResultType, ResultCount> generateDefaultResults() {
        Map<ResultType, ResultCount> results = new EnumMap<>(ResultType.class);

        for (ResultType type : ResultType.values()) {
            results.put(type, new ResultCount(0));
        }

        return results;
    }

    public String getName() {
        return name;
    }

    public Map<ResultType, ResultCount> getMatchResult() {
        return new EnumMap<>(matchResult);
    }

    @Override
    public String toString() {
        return "DealerMatchResult{" +
                "matchResult=" + matchResult +
                ", name='" + name + '\'' +
                '}';
    }
}
