package blackjack.domain.game;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;

public class Referee {

    public Map<ResultType, ResultCount> generateDealerResult(Dealer dealer, Collection<Player> players) {
        Map<ResultType, ResultCount> results = generateDefaultResults();

        for (Player player : players) {
            ResultType result = dealer.compareWith(player);
            results.get(result).increment();
        }

        return results;
    }

    public ResultType generatePlayerResult(Player player, Participant other) {
        return player.compareWith(other);
    }

    private Map<ResultType, ResultCount> generateDefaultResults() {
        Map<ResultType, ResultCount> results = new EnumMap<>(ResultType.class);

        for (ResultType type : ResultType.values()) {
            results.put(type, new ResultCount(0));
        }

        return results;
    }
}