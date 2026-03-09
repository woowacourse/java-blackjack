package blackjack.model;

import static blackjack.model.BlackjackConfig.DEALER_STAND_SCORE;

import java.util.EnumMap;

public class Dealer extends User {

    private final EnumMap<GameResult, Integer> gameResults;

    public Dealer() {
        super("딜러");
        gameResults = new EnumMap<>(GameResult.class);
    }

    public EnumMap<GameResult, Integer> getGameResults() {
        return new EnumMap<>(gameResults);
    }

    public void addResult(GameResult gameResult) {
        gameResults.put(gameResult, gameResults.getOrDefault(gameResult, 0) + 1);
    }

    public boolean canHit() {
        return getScore() < DEALER_STAND_SCORE;
    }
}

