package blackjack.model.user;

import blackjack.model.gameresult.GameResult;
import java.util.EnumMap;

public class Dealer extends User {

    private static final int DEALER_ADD_CARD_STAND = 17;

    private final EnumMap<GameResult, Integer> gameResults;

    public Dealer() {
        super("딜러");
        gameResults = new EnumMap<>(GameResult.class);
    }

    public EnumMap<GameResult, Integer> getGameResults() {
        return gameResults;
    }

    public void addResult(GameResult gameResult) {
        gameResults.merge(gameResult, 1, Integer::sum);
    }

    @Override
    public boolean isHitAvailable() {
        return totalScore() < DEALER_ADD_CARD_STAND;
    }
}
