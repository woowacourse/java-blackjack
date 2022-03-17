package blackjack.domain.card;

public enum Suit {

    SPADE("♠"),
    HEART("♥"),
    CLOVER("♣"),
    DIAMOND("♦"),
    ;

    private final String suit;

    Suit(final String suit) {
        this.suit = suit;
    }

    public String getSuit() {
        return suit;
    }
}
