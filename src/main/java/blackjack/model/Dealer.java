package blackjack.model;

import java.util.EnumMap;

public class Dealer extends User {
    private final EnumMap<GameResult, Integer> gameResults;

    public Dealer() {
        super("딜러");
        gameResults = new EnumMap<>(GameResult.class);
    }

    public EnumMap<GameResult, Integer> getGameResults() {
        return gameResults;
    }

    public void addResult(GameResult gameResult) {
        gameResults.put(gameResult, gameResults.getOrDefault(gameResult, 0) + 1);
    }
}

