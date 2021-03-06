package blackjack.domain.card;

public enum CardValue {
    ACE("A", 1), TWO("2", 2), THREE("3", 3), FOUR("4", 4),
    FIVE("5", 5), SIX("6", 6), SEVEN("7", 7), EIGHT("8", 8),
    NINE("9", 9), TEN("10", 10), QUEEN("Q", 10), JACK("J", 10),
    KING("K", 10);

    private final String value;
    private final int point;

    CardValue(String value, int point) {
        this.value = value;
        this.point = point;
    }

    public int getPoint() {
        return this.point;
    }

    public String getValue() {
        return value;
    }
}
