package card;

public enum Suit {
    HEART("하트"),
    CLOVER("클로버"),
    SPADE("스페이드"),
    DIAMOND("다이아몬드");

    private final String value;

    Suit(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
