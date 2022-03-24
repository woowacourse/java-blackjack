package blakjack.domain.card;

public final class Card {
    private final Suit suit;
    private final Face face;

    Card(final Suit suit, final Face face) {
        this.suit = suit;
        this.face = face;
    }

    public boolean isAce() {
        return face.isAce();
    }

    public int getScore() {
        return face.getScore();
    }

    public String getSuit() {
        return suit.getValue();
    }

    public String getFace() {
        return face.getValue();
    }
}
