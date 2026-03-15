package domain.card;

public class Help {
    private final Shape shape;
    private final Number number;

    public Help(Shape shape, Number number) {
        this.shape = shape;
        this.number = number;
    }

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
