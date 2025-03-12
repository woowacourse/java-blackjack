package blackjack.domain;

import java.util.Objects;

public class GameScore {
    private final int value;
    private final int cardsCount;

    private static final GameScore BLACKJACK_SCORE = new GameScore(21, 2);
    private static final GameScore DEALER_HIT_SCORE = new GameScore(16, 0);

    public GameScore(int value, int cardsCount) {
        this.value = value;
        this.cardsCount = cardsCount;
    }

    public GameScore addAce() {
        GameScore gameScore = new GameScore(this.value + 10, this.cardsCount);
        if (gameScore.isBust()) {
            return this;
        }
        return gameScore;
    }

    public boolean isBust() {
        return this.isBiggerThan(BLACKJACK_SCORE);
    }

    public boolean isBlackJack() {
        return this.equals(GameScore.BLACKJACK_SCORE);
    }

    public boolean isBiggerThan(GameScore dealerScore) {
        return this.value > dealerScore.value;
    }

    public boolean isLessThan(GameScore dealerScore) {
        return this.value < dealerScore.value;
    }

    public boolean isDealerHit() {
        return this.isLessThan(DEALER_HIT_SCORE);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        GameScore gameScore = (GameScore) o;
        return value == gameScore.value && cardsCount == gameScore.cardsCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, cardsCount);
    }
}
