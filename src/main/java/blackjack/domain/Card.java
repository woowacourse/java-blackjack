package blackjack.domain;

public class Card {

    private final Shape shape;
    private final Denomination denomination;

    public Card(final Shape shape, final Denomination denomination) {
        this.shape = shape;
        this.denomination = denomination;
    }
}
