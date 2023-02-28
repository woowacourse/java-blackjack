package domain;

public enum Suit {
    DIAMOND("다이아몬드"),
    SPADE("스페이드"),
    HEART("하트"),
    CLUB("클로버"),
    ;

    private final String value;

    Suit(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
