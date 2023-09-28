package model;

public class Card {

    private final Name type;
    private final int score;

    private Card(final Name type, final int score) {
        this.type = type;
        this.score = score;
    }

    public static Card createDefault(final Name type, final int score) {
        return new Card(type, score);
    }
}
