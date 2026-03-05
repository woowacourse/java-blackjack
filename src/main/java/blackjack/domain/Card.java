package blackjack.domain;

public class Card {

    private CardValue value;
    private Shape shape;

    public Card(CardValue value, Shape shape) {
        this.value = value;
        this.shape = shape;
    }
}
