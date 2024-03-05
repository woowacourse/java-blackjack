package domain;

public class Card {

    private final Letter letter;
    private final Mark mark;

    public Card(Letter letter, Mark mark) {
        this.letter = letter;
        this.mark = mark;
    }

    public int getValue() {
        return letter.getValue();
    }
}
