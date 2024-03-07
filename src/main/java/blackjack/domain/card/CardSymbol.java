package blackjack.domain.card;

public enum CardSymbol {
    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    HEART("하트"),
    CLUB("클로버");

    private final String value;

    CardSymbol(String value) {
        this.value = value;
    }

    public String get() {
        return value;
    }
}
