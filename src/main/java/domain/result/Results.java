package domain.result;

import domain.gamer.Dealer;
import domain.gamer.Player;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

public class Results {
    private final Map<WinLose, List<Player>> playerResults;
    private final Map<WinLose, Integer> dealerResults;

    public Results(List<Player> players, Dealer dealer) {
        this.playerResults = generatePlayerResults(players, dealer);
        this.dealerResults = generateDealerResult(players, dealer);
    }

    private Map<WinLose, List<Player>> generatePlayerResults(List<Player> players, Dealer dealer) {
        return players.stream()
                .collect(groupingBy(player -> player.determineWinLose(dealer)));
    }

    private Map<WinLose, Integer> generateDealerResult(List<Player> players, Dealer dealer) {
        return players.stream()
                .collect(groupingBy(player -> player.determineWinLose(dealer).reverse(), summingInt(x -> 1)));
    }

    public Map<WinLose, List<Player>> getPlayerResults() {
        return playerResults;
    }

    public Map<WinLose, Integer> getDealerResult() {
        return dealerResults;
    }
}
