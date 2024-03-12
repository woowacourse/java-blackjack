package domain.game;

import domain.participant.Dealer;
import domain.participant.Player;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Result {

    private final Map<Player, WinLose> resultMap;

    private Result(Map<Player, WinLose> resultMap) {
        this.resultMap = resultMap;
    }

    public static Result of(List<Player> players, Dealer dealer) {
        return new Result(
            players.stream()
                .collect(Collectors.toMap(
                    player -> player,
                    player -> decideWinLose(player, dealer)))
        );
    }

    private static WinLose decideWinLose(Player player, Dealer dealer) {
        if (player.isBusted()) {
            return WinLose.LOSE;
        }
        if (dealer.isBusted()) {
            return WinLose.WIN;
        }
        return decideWinLoseByScore(player, dealer);
    }

    private static WinLose decideWinLoseByScore(Player player, Dealer dealer) {
        if (dealer.score().isLessThan(player.score())) {
            return WinLose.WIN;
        }
        if (dealer.score().isGreaterThan(player.score())) {
            return WinLose.LOSE;
        }
        return WinLose.TIE;
    }

    public long dealerWinCount() {
        return resultMap.values().stream()
            .filter(value -> value == WinLose.LOSE)
            .count();
    }

    public long dealerLoseCount() {
        return resultMap.values().stream()
            .filter(value -> value == WinLose.WIN)
            .count();
    }

    public long dealerTieCount() {
        return resultMap.values().stream()
            .filter(value -> value == WinLose.TIE)
            .count();
    }

    public WinLose playerWinLose(Player player) {
        return resultMap.get(player);
    }
}
