package domain;

public enum CardShape {
    SPADES("스페이드"),
    HEARTS("하트"),
    DIAMONDS("다이아몬드"),
    CLUBS("클로버");

    private final String name;

    CardShape(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return name;
    }
}
