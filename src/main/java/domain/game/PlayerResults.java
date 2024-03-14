package domain.game;

import domain.participant.Dealer;
import domain.participant.Player;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PlayerResults {

    private final Map<Player, PlayerResult> resultMap;

    private PlayerResults(Map<Player, PlayerResult> resultMap) {
        this.resultMap = resultMap;
    }

    public static PlayerResults of(List<Player> players, Dealer dealer) {
        return new PlayerResults(
            players.stream()
                .collect(Collectors.toMap(
                    player -> player,
                    player -> decideWinLose(player, dealer)))
        );
    }

    private static PlayerResult decideWinLose(Player player, Dealer dealer) {
        if (player.isBust()) {
            return PlayerResult.LOSE;
        }
        if (dealer.isBust()) {
            return PlayerResult.WIN;
        }
        return decideWinLoseByScore(player, dealer);
    }

    private static PlayerResult decideWinLoseByScore(Player player, Dealer dealer) {
        if (dealer.score().isLessThan(player.score())) {
            return PlayerResult.WIN;
        }
        if (dealer.score().isGreaterThan(player.score())) {
            return PlayerResult.LOSE;
        }
        return PlayerResult.TIE;
    }

    public long dealerWinCount() {
        return resultMap.values().stream()
            .filter(value -> value == PlayerResult.LOSE)
            .count();
    }

    public long dealerLoseCount() {
        return resultMap.values().stream()
            .filter(value -> value == PlayerResult.WIN)
            .count();
    }

    public long dealerTieCount() {
        return resultMap.values().stream()
            .filter(value -> value == PlayerResult.TIE)
            .count();
    }

    public PlayerResult playerWinLose(Player player) {
        return resultMap.get(player);
    }
}
