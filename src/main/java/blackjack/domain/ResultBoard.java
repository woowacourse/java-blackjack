package blackjack.domain;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ResultBoard {
    private final Map<Player, Result> resultBoard;

    public ResultBoard(Players players, Dealer dealer) {
        this.resultBoard = resultByUser(players, dealer);
    }

    private Map<Player, Result> resultByUser(Players players, Dealer dealer) {
        return players.players()
                .stream()
                .collect(Collectors.toMap(
                        player -> player, player -> player.produceResult(dealer)
                ));
    }

    private List<Result> results() {
        return resultBoard.values().stream()
                .map(value -> value.reverse(value))
                .collect(Collectors.toList());
    }

    public Map<Result, Integer> dealerResultBoard() {
        return Result.countByResults(results());
    }

    public Map<Player, Result> userResultBoard() {
        return Collections.unmodifiableMap(this.resultBoard);
    }
}
