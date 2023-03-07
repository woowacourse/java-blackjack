package blackjack.domain.card;

import java.util.List;

public class Card {
    final private Shape shape;
    final private Letter letter;

    public Card(final Shape shape, final Letter letter) {
        this.shape = shape;
        this.letter = letter;
    }

    public List<String> getCardName() {
        return List.of(letter.getName(), shape.getValue());
    }

    public int getValue() {
        return this.letter.getValue();
    }

    public boolean isAce() {
        return this.letter.isLetterAce();
    }
}
