package domain;

public class Card {

    private final String face;
    private final String letter;

    public Card(String face, String letter) {
        this.face = face;
        this.letter = letter;
    }

    public int calculateNumber() {
        if (letter.equals("K") || letter.equals("Q") || letter.equals("J")) {
            return 10;
        }
        return 0;
    }
}
