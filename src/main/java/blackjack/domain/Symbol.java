package blackjack.domain;

public enum Symbol {
    SPADE("스페이드"),
    CLOVER("클로버"),
    HEART("하트"),
    DIAMOND("다이아몬드"),
    ;

    private final String symbolName;

    Symbol(String symbolName) {
        this.symbolName = symbolName;
    }

    public String getSymbolName() {
        return symbolName;
    }
}
