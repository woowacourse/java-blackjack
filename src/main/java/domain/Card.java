package domain;

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

    public int getScore() {
        return letter.getScore();
    }
}
