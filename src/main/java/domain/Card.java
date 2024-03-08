package domain;

public class Card {

    private final Letter letter;
    private final Mark mark;

    public Card(Letter letter, Mark mark) {
        this.letter = letter;
        this.mark = mark;
    }

    public boolean isAceCard() {
        return this.letter.isAce();
    }

    public int getLetterValue() {
        return letter.getValue();
    }

    public String getLetterText() {
        return letter.getText();
    }

    public String getMark() {
        return mark.getName();
    }
}
