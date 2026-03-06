package blackjack.domain;

public class Card {

    private CardValue value;
    private Shape shape;

    public Card(CardValue value, Shape shape) {
        this.value = value;
        this.shape = shape;
    }

    public boolean isAce() {
        return value == CardValue.A;
    }

    public String getName() {
        return value.getName() + shape.getName();
    }

    public int getValue() {
        return value.getValue();
    }

}
