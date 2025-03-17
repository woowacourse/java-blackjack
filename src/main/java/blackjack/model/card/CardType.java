package blackjack.model.card;

public enum CardType {
    ACE("A", 1),
    NORMAL_2("2", 2),
    NORMAL_3("3", 3),
    NORMAL_4("4", 4),
    NORMAL_5("5", 5),
    NORMAL_6("6", 6),
    NORMAL_7("7", 7),
    NORMAL_8("8", 8),
    NORMAL_9("9", 9),
    NORMAL_10("10", 10),
    KING("K", 10),
    QUEEN ("Q", 10),
    JACK("J", 10),
    ;

    private final String symbol;
    private final int point;

    CardType(final String symbol, final int point) {
        this.symbol = symbol;
        this.point = point;
    }

    boolean isAce() {
        return this == ACE;
    }

    public String getSymbol() {
        return symbol;
    }

    int getPoint() {
        return point;
    }
}
