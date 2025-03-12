package blackjack.model.card;

import java.awt.Shape;

public class Card {

    private final Denomination denomination;
    private final Shape shape;

    public Card(final Denomination denomination, final Shape shape) {
        this.denomination = denomination;
        this.shape = shape;
    }
}
