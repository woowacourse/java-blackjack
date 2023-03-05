package domain.card;

public class Card {

    private final Suit suit;
    private final Denomination denomination;

    public Card(final Suit suit, final Denomination denomination) {
        this.suit = suit;
        this.denomination = denomination;
    }

    public String getShape() {
        return suit.getShape();
    }

    public int getValue() {
        return denomination.getValue();
    }

    public String getAlias() {
        return denomination.getAlias();
    }
}
