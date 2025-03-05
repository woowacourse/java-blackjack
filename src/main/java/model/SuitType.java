package model;

public enum SuitType {

    DIAMONDS("다이아몬드"),
    HEARTS("하트"),
    SPADES("스페이드"),
    CLUBS("클로버");

    private final String displayName;

    SuitType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
