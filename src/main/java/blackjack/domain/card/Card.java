package blackjack.domain.card;

import java.util.List;

public class Card {
    final private Shape shape;
    final private Letter letter;

    public Card(final Shape shape, final Letter letter) {
        this.shape = shape;
        this.letter = letter;
    }

    public int getValue() {
        return this.letter.getValue();
    }

    public boolean isAce() {
        return this.letter.isLetterAce();
    }

    public Shape getShape() {
        return shape;
    }

    public Letter getLetter() {
        return letter;
    }
}
