package blackjack.domain.card;

public enum CardPattern {
    DIAMOND("다이아몬드"),
    HEART("하트"),
    SPADE("스페이드"),
    CLOVER("클로버");

    private final String value;

    CardPattern(String value) {
        this.value = value;
    }

    public String getName() {
        return value;
    }
}
