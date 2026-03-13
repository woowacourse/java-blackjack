package domain;

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
    public static final int ACE_ADDITIONAL_VALUE = 10;

    Rank(String displayValue, int scoreValue) {
        this.displayValue = displayValue;
        this.scoreValue = scoreValue;
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
