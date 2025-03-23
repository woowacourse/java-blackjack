package blackjack.card;

public enum CardSymbol {
    SPADE("스페이드"),
    HEART("하트"),
    DIAMOND("다이아몬드"),
    COLVER("클로버");

    private final String name;

    CardSymbol(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
