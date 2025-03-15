package blackjack.domain;

public enum CardSuit {
    HEART("하트"),
    DIAMOND("다이아몬드"),
    CLUB("클로버"),
    SPADE("스페이드");

    private final String symbol;

    CardSuit(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return symbol;
    }
}
