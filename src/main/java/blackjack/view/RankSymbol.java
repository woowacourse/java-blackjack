package blackjack.view;

public enum RankSymbol {
    ACE("A"), TWO("2"),
    THREE("3"), FOUR("4"),
    FIVE("5"), SIX("6"),
    SEVEN("7"), EIGHT("8"),
    NINE("9"), TEN("10"),
    JACK("J"), QUEEN("Q"),
    KING("K");

    private final String symbol;

    RankSymbol(String symbol) {
        this.symbol = symbol;
    }

    public static String getMappingSymbol(String rank) {
        return valueOf(rank).symbol;
    }
}
