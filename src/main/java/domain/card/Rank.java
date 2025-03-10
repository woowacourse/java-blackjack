package domain.card;

public enum Rank {
    A(11),
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(10),
    QUEEN(10),
    KING(10);

    private final int value;

    Rank(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public boolean isAce() {
        return value == A.getValue();
    }

    public String formatCardRank() {
        if (this == Rank.A) {
            return "A";
        }
        if (this == Rank.KING) {
            return "K";
        }
        if (this == Rank.QUEEN) {
            return "Q";
        }
        if (this == Rank.JACK) {
            return "J";
        }
        return this.getValue() + "";
    }
}
