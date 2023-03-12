package domain.card;

import domain.hand.Score;

public class Card {

    private final Face face;
    private final Letter letter;

    public Card(Face face, Letter letter) {
        this.face = face;
        this.letter = letter;
    }

    public Letter getLetter() {
        return letter;
    }

    public Face getFace() {
        return face;
    }

    public boolean isA() {
        return letter.isA();
    }

    public Score getScore() {
        return letter.getScore();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Card card = (Card)o;

        if (face != card.face) {
            return false;
        }
        return letter == card.letter;
    }

    @Override
    public int hashCode() {
        int result = face != null ? face.hashCode() : 0;
        result = 31 * result + (letter != null ? letter.hashCode() : 0);
        return result;
    }
}
