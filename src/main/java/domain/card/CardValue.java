package domain.card;

public enum CardValue {

    Q("Q", 10),
    J("J", 10),
    K("K", 10),
    A("A", 11),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10);

    private final String rank;
    private final int value;

    CardValue(String rank, int value) {
        this.rank = rank;
        this.value = value;
    }

    public String getRank() {
        return rank;
    }

    public int getValue() {
        return value;
    }

    public boolean isAce() {
        return this.rank.equals(A.rank);
    }
}
