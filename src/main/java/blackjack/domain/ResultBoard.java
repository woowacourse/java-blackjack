package blackjack.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ResultBoard {
    private final Map<Player, Result> resultBoard;

    public ResultBoard(Dealer dealer, Players players) {
        this.resultBoard = new HashMap<>();
        putResultByUser(dealer, players);
    }

    private void putResultByUser(Dealer dealer, Players players) {
        players.players()
                .forEach(user -> {
                    this.resultBoard.put(user, Result.decide(user.score(), dealer.score()));
                });
    }

    public Map<Result, Integer> dealerResultBoard() {
        return Result.countByResults(groupResult());
    }

    private List<Result> groupResult() {
        return resultBoard.values().stream()
                .map(value -> value.reverse(value))
                .collect(Collectors.toList());
    }

    public Map<Player, Result> userResultBoard() {
        return Collections.unmodifiableMap(this.resultBoard);
    }
}
