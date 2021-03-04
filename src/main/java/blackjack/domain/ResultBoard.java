package blackjack.domain;

import java.util.*;
import java.util.stream.Collectors;

public class ResultBoard {
    private final Map<Player, Result> resultBoard;

    public ResultBoard(Dealer dealer, Players players) {
        this.resultBoard = new HashMap<>();
        putResultByPlayer(dealer, players);
    }

    private void putResultByPlayer(Dealer dealer, Players players) {
        players.players()
                .forEach(player -> {
                    this.resultBoard.put(player, Result.decide(dealer, player));
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

    public Map<Player, Result> playerResultBoard() {
        return Collections.unmodifiableMap(this.resultBoard);
    }
}
