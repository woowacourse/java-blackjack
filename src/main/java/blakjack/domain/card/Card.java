package blakjack.domain.card;

public final class Card {
    private final Suit suit;
    private final Face face;

    public Card(final Suit suit, final Face face) {
        this.suit = suit;
        this.face = face;
    }

    public int getScore() {
        return face.getScore();
    }

    public boolean isAce() {
        return face.isAce();
    }
}
