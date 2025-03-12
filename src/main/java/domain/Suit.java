package domain;

public enum Suit {
    HEARTS("하트"),
    DIAMONDS("다이아몬드"),
    CLUBS("클로버"),
    SPADES("스페이드");

    private final String title;

    Suit(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
