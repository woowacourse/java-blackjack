package blackjack.domain.card;

public class Card {
    private final CardShape shape;
    private final CardValue value;

    public Card(final CardShape shape, final CardValue value) {
        this.shape = shape;
        this.value = value;
    }

    public int getValue() {
        return value.getValue();
    }
}
