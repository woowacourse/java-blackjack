package domain.card;

public enum Suit {
    DIAMOND("다이아몬드"),
    HEART("하트"),
    CLUB("클로버"),
    SPADE("스페이드");

    private final String name;

    Suit(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
