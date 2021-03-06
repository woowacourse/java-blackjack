package blackjack.domain.result;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
                    if (dealer.isBust() || player.isBust()) {
                        this.resultBoard.put(player, player.decideResultByBust(dealer));
                    }
                    this.resultBoard.put(player, player.decideResultByCompare(dealer));
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
