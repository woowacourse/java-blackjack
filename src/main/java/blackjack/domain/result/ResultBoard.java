package blackjack.domain.result;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ResultBoard {
    private final Map<Player, Result> resultBoard;

    public ResultBoard(Map<Player, Result> resultBoard) {
        this.resultBoard = resultBoard;
    }

    public static ResultBoard of(Players players, Dealer dealer) {
        return new ResultBoard(resultByUser(players, dealer));
    }

    private static LinkedHashMap<Player, Result> resultByUser(Players players, Dealer dealer) {
        return players.getPlayers()
                .stream()
                .collect(Collectors.toMap(
                        player -> player,
                        player -> player.produceResult(dealer),
                        (p1, p2) -> p1,
                        LinkedHashMap::new));
    }

    private List<Result> results() {
        return resultBoard.values().stream()
                .map(value -> value.reverse(value))
                .collect(Collectors.toList());
    }

    public Map<Result, Integer> showDealerResultBoard() {
        return Result.countByResults(results());
    }

    public Map<Player, Result> showUserResultBoard() {
        return Collections.unmodifiableMap(this.resultBoard);
    }
}
