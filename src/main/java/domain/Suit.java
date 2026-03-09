package domain;

public enum Suit {
    CLOVER("클로버"),
    HEART("하트"),
    DIAMOND("다이아몬드"),
    SPADE("스페이스")
    ;

    private final String value;

    Suit(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
