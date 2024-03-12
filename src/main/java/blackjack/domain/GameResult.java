package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameResult {

    private final Map<Player, Result> gameResult;

    private GameResult(Map<Player, Result> gameResult) {
        this.gameResult = gameResult;
    }

    public static GameResult of(Dealer dealer, Players players) {
        return new GameResult(makeGameResult(dealer, players));
    }

    private static Map<Player, Result> makeGameResult(Dealer dealer, Players players) {
        Map<Player, Result> gameResult = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            gameResult.put(player, judgeResult(dealer, player));
        }
        return gameResult;
    }

    private static Result judgeResult(Dealer dealer, Player player) {
        if ((dealer.isBust() && player.isBust())
                || dealer.getHandsScore() == player.getHandsScore()) {
            return Result.DRAW;
        }
        if (dealer.isBust()
                || ((player.getHandsScore() > dealer.getHandsScore()) && !player.isBust())) {
            return Result.WIN;
        }
        return Result.LOSE;
    }

    public long getTargetResultCount(Result targetResult) {
        return gameResult.values()
                .stream()
                .filter(result -> result == targetResult)
                .count();
    }

    public Map<Player, Result> getGameResult() {
        return gameResult;
    }
}
