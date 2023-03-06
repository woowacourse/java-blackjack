package model.card;

public class Card {

    private final Shape shape;
    private final Value value;

    public Card(final Shape shape, final Value value) {
        this.shape = shape;
        this.value = value;
    }

    public boolean isAce() {
        return Value.ACE.equals(value);
    }

    @Override
    public String toString() {
        return value.getValue() + shape.getShape();
    }

    public int getValue() {
        return this.value.getValue();
    }

    public String getShape() {
        return this.shape.getShape();
    }

    public String getName() {
        return this.value.getName();
    }
}
