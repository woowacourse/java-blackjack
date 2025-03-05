package domain.card;

public enum CardSymbol {
    HEART("하트"),
    DIAMOND("다이아몬드"),
    SPADE("스페이드"),
    CLOVER("클로버");

    private final String name;

    CardSymbol(final String name) {
        this.name = name;
    }
}
