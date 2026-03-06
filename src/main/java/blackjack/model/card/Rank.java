package blackjack.model.card;

public enum Rank {

    ACE(1, "A"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
    J(10, "J"),
    Q(10, "Q"),
    K(10, "K");

    private final int defaultScore;
    private final String displayName;

    Rank(int defaultScore, String displayName) {
        this.defaultScore = defaultScore;
        this.displayName = displayName;
    }

    public int getDefaultScore() {
        return defaultScore;
    }

    public String getDisplayName() {
        return displayName;
    }
}
