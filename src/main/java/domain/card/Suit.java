package domain.card;

public enum Suit {

    CLOVER("♣️"), SPADE("♠️"), HEART("♥️"), DIAMOND("♦️");

    private final String suit;

    Suit(String suit) {
        this.suit = suit;
    }

    public String getSuit() {
        return suit;
    }
}
