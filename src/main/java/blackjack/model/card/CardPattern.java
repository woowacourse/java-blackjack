package blackjack.model.card;

public enum CardPattern {

    SPADE("스페이드"),
    HEART("하트"),
    DIAMOND("다이아몬드"),
    CLOVER("클로버");

    private final String name;

    CardPattern(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
