package blackjack.card;

public enum CardRank {

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
    JACK(10, "J"),
    QUEEN(10, "Q"),
    KING(10, "K");

    private final int value;
    private final String description;

    CardRank(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public boolean isAce() {
        return this.equals(ACE);
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }
}
