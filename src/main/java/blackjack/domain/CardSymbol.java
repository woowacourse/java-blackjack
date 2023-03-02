package blackjack.domain;

public enum CardSymbol {

    HEARTS("하트"),
    DIAMONDS("다이아몬드"),
    SPADES("스페이드"),
    CLUBS("클로버");

    private final String symbol;

    CardSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
