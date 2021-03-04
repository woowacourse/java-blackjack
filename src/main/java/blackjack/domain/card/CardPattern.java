package blackjack.domain.card;

public enum CardPattern {
    HEART("하트"),
    DIAMOND("다이아몬드"),
    CLOVER("클로버"),
    SPADE("스페이드");

    private final String name;

    CardPattern(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
