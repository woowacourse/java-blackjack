package blackjack.domain.gamer;

import blackjack.domain.ResultCalculator;
import blackjack.domain.ResultType;
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

    public int dealerResult(Dealer dealer) {
        int sum = 0;
        for (Player player : players) {
            ResultType result = ResultCalculator.decideWinner(player, dealer);
            sum += player.calculateProfit(result);
        }
        return -sum;
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

    public Stream<Player> stream() {
        return players.stream();
    }

    @Override
    public Iterator<Player> iterator() {
        return players.iterator();
    }
}
