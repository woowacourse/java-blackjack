package domain.card;

public enum Shape {

    HEARTS("하트"),
    SPADES("스페이드"),
    CLUBS("클로버"),
    DIAMONDS("다이아몬드");

    private final String name;

    Shape(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
