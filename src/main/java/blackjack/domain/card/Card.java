package blackjack.domain.card;

public class Card {
    private final CardShape shape;
    private final CardType type;

    public Card(final CardShape shape, final CardType type) {
        this.shape = shape;
        this.type = type;
    }

    public int getTypeValue() {
        return type.getValue();
    }

    public boolean isAce() {
        return type == CardType.ACE;
    }

    @Override
    public String toString() {
        return type.getDisplayName() + shape.getDisplayName();
    }
}
