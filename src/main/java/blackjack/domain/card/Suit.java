package blackjack.domain.card;

public enum Suit {
    SPADES("스페이드"),
    CLUBS("클로버"),
    DIAMONDS("다이아몬드"),
    HEARTS("하트");

    String name;

    Suit(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
