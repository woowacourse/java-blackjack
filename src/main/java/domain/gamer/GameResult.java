package domain.gamer;

import exception.MissResultException;

import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toMap;

public class GameResult {
    private Map<Player, MatchResult> gameResult;

    public GameResult(Gamers gamers) {
        this.gameResult = gamers.getPlayers().stream()
                .collect(collectingAndThen(toMap(Player -> Player,
                        player -> findMatchResult(player, gamers.getDealer()),
                        (a, b) -> a),
                        LinkedHashMap::new));
    }

    private MatchResult findMatchResult(Player player, Dealer dealer) {
        if (player.isBlackJack() && dealer.isBlackJack()) {
            return MatchResult.DRAW;
        }

        if (player.isBlackJack()) {
            return MatchResult.BLACKJACK;
        }

        if (dealer.isBlackJack()) {
            return MatchResult.LOSE;
        }

        return MatchResult.of(player.score.getScore(), dealer.score.getScore());
    }

    public Map<Player, Money> getPlayersTotalEarning() {
        return gameResult.keySet()
                .stream()
                .collect(toMap(player -> player, player -> gameResult.get(player).
                                getEarnCalculator().apply(player.getMoney()),
                        (a, b) -> a,
                        LinkedHashMap::new));
    }

    public Money getDealerEarning() {
        return getPlayersTotalEarning().values()
                .stream()
                .map(Money::reversion)
                .reduce(Money::sum)
                .orElseThrow(MissResultException::new);
    }

    public Map<Player, MatchResult> getGameResult() {
        return gameResult;
    }
}
