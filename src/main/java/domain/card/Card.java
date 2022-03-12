package domain.card;

public final class Card {
    private final Suit suit;
    private final Face face;

    public Card(Suit suit, Face face) {
        this.suit = suit;
        this.face = face;
    }

    public boolean isAceCard() {
        return face.isAce();
    }

    public int getScore() {
        return face.getScore();
    }
}
