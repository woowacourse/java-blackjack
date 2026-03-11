package blackjack.model;


import java.util.EnumMap;

public class Dealer extends User {

    public static final int DEALER_STAND_SCORE = 17;

    private final EnumMap<GameOutcome, Integer> gameResults;

    public Dealer() {
        super("딜러");
        gameResults = new EnumMap<>(GameOutcome.class);
    }

    public EnumMap<GameOutcome, Integer> getGameResults() {
        return new EnumMap<>(gameResults);
    }

    public void addResult(GameOutcome gameOutcome) {
        gameResults.put(gameOutcome, gameResults.getOrDefault(gameOutcome, 0) + 1);
    }

    public boolean canHit() {
        return getScore() < DEALER_STAND_SCORE;
    }
}

