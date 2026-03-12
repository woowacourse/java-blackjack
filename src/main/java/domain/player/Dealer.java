package domain.player;

import domain.result.GameResult;
import domain.card.Card;

import java.util.HashMap;
import java.util.Map;

public class Dealer extends Player {

    private Map<GameResult, Integer> gameResults = new HashMap<>();

    public Card reveal() {
        return getHand().getFirst();
    }

    public void recordRounds(GameResult gameResult) {
        gameResults.merge(gameResult, 1, Integer::sum);
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
