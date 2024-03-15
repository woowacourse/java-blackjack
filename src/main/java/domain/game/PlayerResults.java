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
                    player -> decideResult(player, dealer)))
        );
    }

    public static PlayerResult decideResult(Player player, Dealer dealer) {
        if (player.isBust()) {
            return PlayerResult.LOSE;
        }
        return decideResultByBlackjack(player, dealer);
    }

    private static PlayerResult decideResultByBlackjack(Player player, Dealer dealer) {
        if (player.isBlackjack() && dealer.isNotBlackjack()) {
            return PlayerResult.BLACKJACK;
        }
        return decideResultByScore(player, dealer);
    }

    private static PlayerResult decideResultByScore(Player player, Dealer dealer) {
        if (dealer.isBust()) {
            return PlayerResult.WIN;
        }
        if (player.hasHigherScoreThan(dealer)) {
            return PlayerResult.WIN;
        }
        if (player.hasLowerScoreThan(dealer)) {
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

    public PlayerResult resultBy(Player player) {
        return resultMap.get(player);
    }
}
