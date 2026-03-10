package domain.card;

public enum Suit {
    HEARTS("하트"),
    DIAMONDS("다이아몬드"),
    CLUBS("클로버"),
    SPADES("스페이드");

    private final String description;

    Suit(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
