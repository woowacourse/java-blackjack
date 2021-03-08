package blackjack.domain.result;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;

import java.util.*;

import static java.util.stream.Collectors.*;

public class ResultBoard {
    private final Map<Player, Result> resultBoard;

    public ResultBoard(Dealer dealer, Players players) {
        this.resultBoard = new LinkedHashMap<>();
        putResultByPlayer(dealer, players);
    }

    private void putResultByPlayer(Dealer dealer, Players players) {
        players.getPlayers()
                .forEach(player -> {
                    if (dealer.isBust() || player.isBust()) {
                        this.resultBoard.put(player, player.decideResultByBust(dealer));
                        return;
                    }
                    this.resultBoard.put(player, player.decideResultByCompare(dealer));
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
