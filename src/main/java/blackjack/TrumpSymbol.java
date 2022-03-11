package blackjack;

public enum TrumpSymbol {
    HEART("하트"),
    DIAMOND("다이아몬드"),
    CLOVER("클로버"),
    SPADE("스페이드"),
    ;

    private final String value;

    TrumpSymbol(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
