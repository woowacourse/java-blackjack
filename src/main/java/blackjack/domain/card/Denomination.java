package blackjack.domain.card;

public enum Denomination {
    DIAMOND("다이아몬드"),
    CLOVER("클로버"),
    HEART("하트"),
    SPADE("스페이드");

    private final String value;

    Denomination(final String pattern) {
        this.value = pattern;
    }

    public String getValue() {
        return value;
    }
}
