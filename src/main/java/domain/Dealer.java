package domain;

import java.util.HashMap;
import java.util.Map;

public class Dealer extends Participant{

    private final int DEALER_HIT_NUM = 16;

    private final Map<GameResult, Integer> gameResults = new HashMap<>();

    private Map<GameResult, Integer> gameResults = new HashMap<>();

    public Card reveal() {
        return hand.getFirst();
    }

    public boolean isHit(int score) {
        return score <= DEALER_HIT_NUM;
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

    public void setRounds(GameResult gameResult) {
        gameResults.put(gameResult, gameResults.getOrDefault(gameResult,0) + 1);
    }

    public void setRounds(GameResult gameResult) {
        gameResults.put(gameResult, gameResults.getOrDefault(gameResult,0) + 1);
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

    public void setRounds(GameResult gameResult) {
        gameResults.put(gameResult, gameResults.getOrDefault(gameResult,0) + 1);
    }
}
