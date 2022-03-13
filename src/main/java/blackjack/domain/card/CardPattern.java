package blackjack.domain.card;

public enum CardPattern {

    SPADE("스페이드"),
    HEART("하트"),
    CLOVER("클로버"),
    DIAMOND("다이아몬드");

    private final String name;

    CardPattern(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
