package blackjack.domain.card;

public enum Shape {
    DIAMOND("다이아몬드"),
    SPADE("스페이드"),
    CLOVER("클로버"),
    HEART("하트");

    private final String symbol;

    Shape(final String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
