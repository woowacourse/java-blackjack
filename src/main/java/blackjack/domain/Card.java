package blackjack.domain;

public class Card {
    private final CardShape shape;
    private final CardNumber number;

    public Card(final CardShape shape, final CardNumber number) {
        this.shape = shape;
        this.number = number;
    }
}
