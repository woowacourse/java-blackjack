package blackjack.domain.result;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class ResultBoard {
    private final Map<Player, Result> resultBoard;

    public ResultBoard(Dealer dealer, List<Player> players) {
        this.resultBoard = new LinkedHashMap<>();
        putResultByPlayer(dealer, players);
    }

    private void putResultByPlayer(Dealer dealer, List<Player> players) {
        players.forEach(player -> {
            this.resultBoard.put(player, player.decide(dealer));
        });
    }

    public Map<Result, Integer> getDealerResultBoard() {
        return Collections.unmodifiableMap(Result.countByResults(results()));
    }

    private List<Result> results() {
        return this.resultBoard.values().stream()
                .map(value -> value.reverse(value))
                .collect(toList());
    }

    public Map<Player, Result> getPlayerResultBoard() {
        return Collections.unmodifiableMap(this.resultBoard);
    }
}
