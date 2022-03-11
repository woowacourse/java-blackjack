package domain.card;

public class Card {
    private final SUIT suit;
    private final FACE face;

    public Card(SUIT suit, FACE face) {
        this.suit = suit;
        this.face = face;
    }

    public int getScore() {
        return face.getScore();
    }

    public boolean isAceCard() {
        return face.isAce();
    }
}
