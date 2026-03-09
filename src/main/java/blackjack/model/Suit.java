package blackjack.model;

public enum Suit {

    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    HEART("하트"),
    CLOVER("클로버"),
    ;

    private final String format;

    Suit(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }
}
