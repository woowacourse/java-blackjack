package blackjack.domain;

public class Card {
    private final Denomination denomination;
    private final String shape;

    private Card(final Denomination denomination, final String shape) {
        this.denomination = denomination;
        this.shape = shape;
    }

    public static Card of(final Denomination denomination, final String shape) {
        return new Card(denomination, shape);
    }

    public Denomination getDenomination() {
        return denomination;
    }
}