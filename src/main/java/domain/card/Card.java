package domain.card;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return suit == card.suit && face == card.face;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, face);
    }

    @Override
    public String toString() {
        return "Card{" +
                "suit=" + suit +
                ", face=" + face +
                '}';
    }
}
