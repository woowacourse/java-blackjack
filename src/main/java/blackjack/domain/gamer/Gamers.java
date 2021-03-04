package blackjack.domain.gamer;

import blackjack.domain.ResultCalculator;
import blackjack.domain.ResultType;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Gamers {

    private final List<Player> players;
    private final Dealer dealer;


    public Gamers(List<Player> players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public Map<ResultType, Integer> resultWithCount() {
        Map<ResultType, Integer> result = new EnumMap<>(ResultType.class);
        for (Player player : players) {
            ResultType switchedResult = ResultCalculator.decideWinner(player, dealer).switchPosition();
            result.put(switchedResult, result.getOrDefault(switchedResult, 0) + 1);
        }
        return result;
    }

    public Map<String, ResultType> resultWithName() {
        Map<String, ResultType> result = new HashMap<>();
        for (Player player : players) {
            ResultType resultType = ResultCalculator.decideWinner(player, dealer);
            result.put(player.getName(), resultType);
        }
        return result;
    }

    public List<Player> players() {
        return players;
    }

    public Dealer dealer() {
        return dealer;
    }
}
