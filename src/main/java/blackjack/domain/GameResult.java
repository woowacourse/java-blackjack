package blackjack.domain;

import blackjack.domain.participant.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameResult {

    private final Map<Player, BetAmount> gameResult;

    private GameResult(Map<Player, BetAmount> gameResult) {
        this.gameResult = new HashMap<>(gameResult);
    }

    public static GameResult of(List<Player> players, List<BetAmount> betAmounts) {
        Map<Player, BetAmount> gameResult = new HashMap<>();
        for (int index = 0; index < players.size(); index++) {
            gameResult.put(players.get(index), betAmounts.get(index));
        }
        return new GameResult(gameResult);
    }

    public void calculateResultEachPlayer(Player player, double yield) {
        BetAmount betAmount = gameResult.get(player);
        gameResult.put(player, betAmount.multiple(yield));
    }

    public BetAmount eachPlayer(Player player) {
        return gameResult.get(player);
    }

    public BetAmount dealer() {
        BetAmount betAmount = BetAmount.from(0);
        for (BetAmount playerBetAmount : gameResult.values()) {
            betAmount = betAmount.add(playerBetAmount);
        }
        return betAmount.convertSign();
    }
}
