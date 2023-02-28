package domain;

public class Card {

    private final String face;
    private final String letter;

    public Card(String face, String letter) {
        this.face = face;
        this.letter = letter;
    }

    public String getLetter() {
        return letter;
    }
}
