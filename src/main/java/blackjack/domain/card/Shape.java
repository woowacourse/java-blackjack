package blackjack.domain.card;

public enum Shape {
    CLUBS("클로버"),
    DIAMONDS("다이아몬드"),
    SPACES("스페이스"),
    HEARTS("하트");

    private final String name;

    Shape(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
