package blackjack.domain;

public final class Card {

    private final CardShape shape;
    private final CardValue value;

    public Card(CardShape shape, CardValue value) {
        this.shape = shape;
        this.value = value;
    }
}
