package blakjack.domain.card;

public enum Suit {
    CLUB("클로버"),
    HEART("하트"),
    DIAMOND("다이아몬드"),
    SPADE("스페이드"),
    ;

    private final String value;

    Suit(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
