package domain.card;

public enum Suit {
    HEARTS("하트"),
    DIAMONDS("다이아몬드"),
    CLUBS("클로버"),
    SPADES("스페이드");

    private final String name;

    Suit(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
