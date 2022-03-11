package blackjack.domain.card;

public enum CardSymbol {

    DIAMOND("다이아몬드"),
    SPADE("스페이드"),
    HEART("하트"),
    CLUB("클로버");

    private final String name;

    CardSymbol(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
