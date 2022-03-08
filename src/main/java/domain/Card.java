package domain;

public class Card {
    private final CardShape shape;
    private final CardNumber number;

    public Card(CardShape shape, CardNumber number) {
        this.shape = shape;
        this.number = number;
    }

    public CardNumber getNumber() {
        return this.number;
    }
}
