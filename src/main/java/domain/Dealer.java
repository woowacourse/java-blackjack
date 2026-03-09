package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dealer extends Player {

    private final List<Card> hand = new ArrayList<>();

    private Map<GameResult, Integer> gameResults = new HashMap<>();

    public Card reveal() {
        return hand.getFirst();
    }

    public void setRounds(GameResult gameResult) {
        gameResults.put(gameResult, gameResults.getOrDefault(gameResult,0) + 1);
    }

    public Map<GameResult, Integer> getGameResults() {
        return gameResults;
    }

    public int getWinRounds() {
        return gameResults.getOrDefault(GameResult.WIN,0);
    }

    public int getDrawRounds() {
        return gameResults.getOrDefault(GameResult.DRAW,0);
    }

    public int getLoseRounds() {
        return gameResults.getOrDefault(GameResult.LOSE,0);
    }
}
