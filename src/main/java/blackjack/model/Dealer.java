package blackjack.model;

import java.util.EnumMap;

public class Dealer extends User {
    private EnumMap<GameResult, Integer> gameResults;

    public Dealer(String name) {
        super(name);
        gameResults = new EnumMap<>(GameResult.class);
    }

    public EnumMap<GameResult, Integer> getGameResults() {
        return gameResults;
    }
}
