package blackjack.card;

public class Card {

    private final Shape shape;
    private final Denomination denomination;

    public Card(final Shape shape, final Denomination denomination) {
        this.shape = shape;
        this.denomination = denomination;
    }

    public boolean isAce() {
        return denomination == Denomination.ACE;
    }

    public int getNumber() {
        return denomination.getNumber();
    }
}
