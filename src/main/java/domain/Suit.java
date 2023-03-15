package domain;

public enum Suit {
    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    HEART("하트"),
    CLUB("클로버"),
    ;

    private final String value;

    Suit(final String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
