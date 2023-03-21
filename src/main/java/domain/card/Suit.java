package domain.card;

public enum Suit {
    HEART("하트"),
    SPADE("스페이드"),
    CLOVER("클로버"),
    DIAMOND("다이아몬드");

    private final String description;

    Suit(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
