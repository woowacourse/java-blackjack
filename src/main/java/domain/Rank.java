package domain;

import static domain.Result.BLACKJACK_MAX_NUMBER;

public enum Rank {
    ACE("A", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    J("J", 10),
    Q("Q", 10),
    K("K", 10);

    private final String displayValue;
    private final int scoreValue;
    public static final int ACE_MAX_VALUE = 11;
    public static final int ACE_MIN_VALUE = 1;

    Rank(String displayValue, int scoreValue) {
        this.displayValue = displayValue;
        this.scoreValue = scoreValue;
    }

    public static int decideAceValue(int sum, int leftAce) {
        if (sum + ACE_MAX_VALUE <= calculateThreshold(leftAce)) {
            return ACE_MAX_VALUE;
        }
        return ACE_MIN_VALUE;
    }

    private static int calculateThreshold(int leftAce) {
        return BLACKJACK_MAX_NUMBER + 1 - leftAce;
    }

    public int getScoreValue() {
        return scoreValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public boolean isAce() {
        return this == Rank.ACE;
    }
}
