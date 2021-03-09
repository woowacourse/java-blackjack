package blackjack.domain.gamer;

import blackjack.domain.ResultCalculator;
import blackjack.domain.ResultType;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Players implements Iterable<Player> {

    private final List<Player> players;


    public Players(List<Player> players) {
        this.players = players;
    }

    public Map<ResultType, Integer> resultWithCount(Dealer dealer) {
        Map<ResultType, Integer> result = new EnumMap<>(ResultType.class);
        for (Player player : players) {
            ResultType switchedResult = ResultCalculator.decideWinner(player, dealer)
                    .switchPosition();
            result.put(switchedResult, result.getOrDefault(switchedResult, 0) + 1);
        }
        return result;
    }

    public Map<String, Integer> resultWithName(Dealer dealer) {
        Map<String, Integer> result = new HashMap<>();
        for (Player player : players) {
            ResultType resultType = ResultCalculator.decideWinner(player, dealer);
            int profit = player.calculateProfit(resultType);
            result.put(player.getName(), profit);
        }
        return result;
    }

    public List<Player> players() {
        return players;
    }

    public Stream<Player> stream() {
        return players.stream();
    }

    @Override
    public Iterator<Player> iterator() {
        return players.iterator();
    }
}
