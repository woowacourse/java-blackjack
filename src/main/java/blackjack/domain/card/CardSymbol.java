package blackjack.domain.card;

public enum CardSymbol {
    SPADE("스페이드"),
    HEART("하트"),
    CLUB("클럽"),
    DIAMOND("다이아몬드");

    private final String symbol;

    CardSymbol(final String symbol) {
        this.symbol = symbol;
    }
}
