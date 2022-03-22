package blackjack.domain.card;

public enum CardSymbol {
    SPADES("스페이드"),
    CLUBS("클로버"),
    HEARTS("하트"),
    DIAMONDS("다이아몬드"),
    ;

    private final String symbolName;

    CardSymbol(String symbolName) {
        this.symbolName = symbolName;
    }

    public String getSymbolName() {
        return symbolName;
    }
}
