package blackjack.domain.card;

public enum SymbolCandidate {

    CLOVER("클로버"),
    HEART("하트"),
    SPADE("스페이드"),
    DIAMOND("다이아몬드");

    private String symbol;

    SymbolCandidate(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return this.symbol;
    }
}
