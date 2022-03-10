package blackjack.model;

public enum Suit {
    DIAMOND("다이아몬드"), HEART("하트"),
    SPADE("스페이드"), CLOVER("클로버");

    private final String symbol;

    Suit(String symbol) {
        this.symbol = symbol;
    }

    public String symbol() {
        return symbol;
    }
}
