package blackjack.domain.card;

public class Card {
    private final CardShape shape;
    private final CardType type;

    public Card(final CardShape shape, final CardType type) {
        this.shape = shape;
        this.type = type;
    }

    public int getValue() {
        return type.getValue();
    }

    public boolean isAce() {
        return type == CardType.ACE;
    }
}
