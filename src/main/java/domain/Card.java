package domain;

public class Card {

    private final Face face;
    private final String letter;

    public Card(Face face, String letter) {
        this.face = face;
        this.letter = letter;
    }

    public String getLetter() {
        return letter;
    }

    public Face getFace() {
        return face;
    }
}
