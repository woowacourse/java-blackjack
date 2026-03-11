package domain;

import static domain.Constant.*;

public enum Rank {
    ACE("A", new Score(ACE_MIN_VALUE)),
    TWO("2", new Score(2)),
    THREE("3", new Score(3)),
    FOUR("4", new Score(4)),
    FIVE("5", new Score(5)),
    SIX("6", new Score(6)),
    SEVEN("7", new Score(7)),
    EIGHT("8", new Score(8)),
    NINE("9", new Score(9)),
    TEN("10", new Score(10)),
    J("J", new Score(10)),
    Q("Q", new Score(10)),
    K("K", new Score(10));

    private final String displayValue;
    private final Score score;

    Rank(String displayValue, Score scoreValue) {
        this.displayValue = displayValue;
        this.score = scoreValue;
    }

    public static Score decideAceValue(Score sum, int leftAce) {
        if (sum.getValue() + ACE_MAX_VALUE <= calculateThreshold(leftAce).getValue()) {
            return new Score(ACE_MAX_VALUE);
        }
        return new Score(ACE_MIN_VALUE);
    }

    public boolean isAce() {
        return this == Rank.ACE;
    }

    public Score getScore() {
        return score;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    private static Score calculateThreshold(int leftAce) {
        return BLACKJACK_MAX_NUMBER.add(new Score(1 - leftAce));
    }
}
