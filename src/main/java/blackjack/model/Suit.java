package blackjack.model;

public enum Suit {

    HEARTS("하트"),
    SPADES("스페이드"),
    CLUBS("클로버"),
    DIAMONDS("다이아몬드"),
    ;

    private final String label;

    Suit(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
