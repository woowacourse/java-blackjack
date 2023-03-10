package blackjack.domain.card;

public enum Suit {
    HEART("하트"),
    DIAMOND("다이아몬드"),
    CLOVER("클로버"),
    SPADE("스페이드");

    private final String name;

    Suit(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
