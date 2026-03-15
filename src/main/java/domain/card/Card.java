package domain.card;

public class Card {
    private final Shape shape;
    private final Number number;

    public Card(Shape shape, Number number) {
        this.shape = shape;
        this.number = number;
    }

    //ToDO:
    public int getScore() {
        return number.getValue();
    }

    public String getCardInfo() {
        return number.getDisplayName() + shape.getShape();
    }

    public boolean isAce() {
        return this.number.equals(Number.ACE);
    }
}
