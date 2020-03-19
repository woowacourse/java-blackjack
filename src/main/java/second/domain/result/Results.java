package second.domain.result;

import second.domain.gamer.Dealer;
import second.domain.gamer.Player;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

public class Results {
    private final Map<ResultType, List<Player>> playerResults;
    private final Map<ResultType, Integer> dealerResults;

    private Results(final List<Player> players, final Dealer dealer) {
        this.playerResults = generatePlayerResults(players, dealer);
        this.dealerResults = generateDealerResult(players, dealer);
    }

    public static Results generate(final List<Player> players, final Dealer dealer) {
        return new Results(players, dealer);
    }

    private Map<ResultType, List<Player>> generatePlayerResults(final List<Player> players, final Dealer dealer) {
        return players.stream()
                .collect(groupingBy(player -> ResultType.from(player, dealer)));
    }

    private Map<ResultType, Integer> generateDealerResult(final List<Player> players, final Dealer dealer) {
        // TODO : 이부분 문제 존재 원래는 dealer, player의 매개변수순
        return players.stream()
                .collect(groupingBy(player -> ResultType.from(player, dealer), summingInt(x -> 1)));
    }

    public Map<ResultType, List<Player>> getPlayerResults() {
        return playerResults;
    }

    public Map<ResultType, Integer> getDealerResult() {
        return dealerResults;
    }
}
