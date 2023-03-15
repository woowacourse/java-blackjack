package domain.deck;

public enum Suit {

    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    HEART("하트"),
    CLOVER("클로버");

    private final String suit;

    Suit(final String suit) {
        this.suit = suit;
    }

    public String getSuit() {
        return suit;
    }
}
