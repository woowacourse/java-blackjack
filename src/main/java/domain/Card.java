package domain;

public class Card {

    private final Face face;
    private final Letter letter;

    public Card(Face face, String letter) {
        this.face = face;
        this.letter = Letter.of(letter);
    }

    public Card(Face face, Letter letter) {
        this.face = face;
        this.letter = letter;
    }

    public String getLetter() {
        return letter.getLetter();
    }

    public Letter letter() {
        return letter;
    }

    public Face getFace() {
        return face;
    }

    public boolean isNotA() {
        return letter.isNotA();
    }
}
