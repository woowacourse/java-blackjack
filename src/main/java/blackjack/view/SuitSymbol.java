package blackjack.view;

public enum SuitSymbol {
    DIAMOND("다이아몬드"), HEART("하트"),
    SPADE("스페이드"), CLOVER("클로버");

    private final String symbol;

    SuitSymbol(String symbol) {
        this.symbol = symbol;
    }

    public static String getMappingSymbol(String suit) {
        return valueOf(suit).symbol;
    }
}
