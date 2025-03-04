package domain;

import java.util.Arrays;
import java.util.EnumMap;

public class Dealer {
    private final CardHand cardHand;
    private final EnumMap<GameResult, Integer> gameResult = new EnumMap<>(GameResult.class);

    {
        Arrays.stream(GameResult.values())
                .forEach(result -> gameResult.put(result, 0));
    }

    public Dealer(CardHand cardHand) {
        this.cardHand = cardHand;
    }

    public int calculateScore() {
        return cardHand.calculateScore();
    }

    public boolean isBust() {
        return cardHand.isBust();
    }

    public boolean isBlackJack() {
        return cardHand.isBlackJack();
    }

    public boolean isHit() {
        return cardHand.isDealerHit();
    }

    public void recordGameResult(GameResult result) {
        gameResult.put(result, gameResult.get(result) + 1);
    }

    public int getGameResultCount(GameResult result) {
        return gameResult.get(result);
    }
}
