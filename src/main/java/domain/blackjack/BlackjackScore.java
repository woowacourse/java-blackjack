package domain.blackjack;

import domain.card.Card;
import domain.card.Cards;
import java.util.Objects;

public class BlackjackScore {
    private static final BlackjackScore MAX_SCORE = new BlackjackScore(21);
    private static final BlackjackScore ACE_GAP = new BlackjackScore(10);

    private final int value;

    private BlackjackScore(int value) {
        this.value = value;
    }

    public static BlackjackScore from(int value) {
        return new BlackjackScore(value);
    }

    public static BlackjackScore getMaxScore() {
        return MAX_SCORE;
    }

    public static BlackjackScore from(Cards cards) {
        int totalScore = cards.getCards().stream()
                .mapToInt(Card::getScore)
                .sum();

        BlackjackScore sumOfBlackjackScore = new BlackjackScore(totalScore);
        return applyAce(sumOfBlackjackScore, cards.countAce());
    }

    private static BlackjackScore applyAce(BlackjackScore blackjackScore, int aceCount) {
        while (blackjackScore.isGreaterThan(MAX_SCORE) && aceCount > 0) {
            int subtractedValue = blackjackScore.value - ACE_GAP.value;
            blackjackScore = new BlackjackScore(subtractedValue);
            aceCount--;
        }

        return blackjackScore;
    }

    public boolean isGreaterThan(BlackjackScore other) {
        return this.value > other.value;
    }

    public boolean isEqualTo(BlackjackScore other) {
        return this.value == other.value;
    }

    public Result compete(BlackjackScore otherBlackjackScore) {
        if (this.isGreaterThan(otherBlackjackScore)) {
            return Result.WIN;
        }

        if (this.isEqualTo(otherBlackjackScore)) {
            return Result.DRAW;
        }

        return Result.LOSE;
    }

    public boolean isBusted() {
        return this.isGreaterThan(MAX_SCORE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BlackjackScore blackjackScore = (BlackjackScore) o;
        return getValue() == blackjackScore.getValue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }

    public int getValue() {
        return value;
    }
}
