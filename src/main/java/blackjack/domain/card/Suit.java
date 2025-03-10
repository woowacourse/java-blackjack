package blackjack.domain.card;

public enum Suit {
    SPADE("스페이드"),
    HEART("하트"),
    DIAMOND("다이아몬드"),
    CLUB("클로버");

    private final String description;

    private Suit(final String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
