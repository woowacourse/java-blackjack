package blackjack.domain.card;

public class Card {
    private final Shape shape;
    private final Letter letter;
    private boolean isOpen;

    public Card(final Shape shape, final Letter letter) {
        this.shape = shape;
        this.letter = letter;
        this.isOpen = true;
    }

    public int getScore() {
        return this.letter.getValue();
    }

    public boolean isAce() {
        return this.letter.isAce();
    }

    public Shape getShape() {
        return shape;
    }

    public Letter getLetter() {
        return letter;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void closeCard() {
        this.isOpen = false;
    }

    public void openCard() {
        this.isOpen = true;
    }
}
