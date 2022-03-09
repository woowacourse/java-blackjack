package blackJack.domain.card;

public enum Symbol {
    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    HEART("하트"),
    CLOVER("클로버")
    ;

    private final String symbol;

    Symbol(String symbol) {
        this.symbol = symbol;
    }
}
