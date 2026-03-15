package blackjack.model.card;

public enum Suit {

    SPADE("스페이드"),
    HEART("하트"),
    CLOVER("클로버"),
    DIAMOND("다이아몬드");

    private final String displayName;

    Suit(final String suit) {
        this.displayName = suit;
    }

    public String getDisplayName() {
        return displayName;
    }
}
