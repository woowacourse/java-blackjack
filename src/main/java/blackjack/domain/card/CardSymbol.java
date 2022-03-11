package blackjack.domain.card;

public enum CardSymbol {
    SPADE("스페이드"),
    CLOVER("클로버"),
    HEART("하트"),
    DIAMOND("다이아몬드"),
    ;

    private final String symbolName;

    CardSymbol(String symbolName) {
        this.symbolName = symbolName;
    }

    @Override
    public String toString() {
        return symbolName;
    }
}
