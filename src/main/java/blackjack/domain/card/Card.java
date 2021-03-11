package blackjack.domain.card;

public class Card {

    private final Face face;
    private final Suit suit;

    public Card(final Suit suit, final Face face) {
        this.suit = suit;
        this.face = face;
    }

    public int getFaceValueAsInt() {
        return this.face.getValue();
    }

    public String getSuitAsString() {
        return this.suit.getSuit();
    }

    public boolean isAce() {
        return this.face.equals(Face.ACE);
    }
}
