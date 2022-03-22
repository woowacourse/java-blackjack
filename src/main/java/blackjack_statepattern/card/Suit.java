package blackjack_statepattern.card;

public enum Suit {
    SPADES("스페이드"),
    CLUBS("클로버"),
    HEARTS("하트"),
    DIAMONDS("다이아몬드");

    private final String name;

    Suit(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
