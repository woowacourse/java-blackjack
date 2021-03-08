package blackjack.domain.card;

public enum Suit {

    HEART("하트"),
    DIAMOND("다이아몬드"),
    SPADE("스페이드"),
    CLOVER("클로버");

    private final String suit;

    Suit(final String suit) {
        this.suit = suit;
    }

    public String getSuit() {
        return this.suit;
    }
}