package blackjack.domain;

public enum Suit {
    CLUBS("클로버"),
    DIAMONDS("다이아몬드"),
    HEARTS("하트"),
    SPADES("스페이드"),
    ;

    private final String name;

    Suit(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
