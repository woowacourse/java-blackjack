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
