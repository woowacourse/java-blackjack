package blackjack.domain.card;

public class Card {

    private final Shape shape;
    private final Number number;

    public Card(final Shape shape, final Number number) {
        this.shape = shape;
        this.number = number;
    }

    public boolean isAce() {
        return number.equals(Number.ACE);
    }

    public Shape getShape() {
        return shape;
    }

    // TODO : string, number 어떤걸 get 해야할까?
    public Number getNumber() {
        return number;
    }

    public int getPoint() {
        return number.getValue();
    }
}
