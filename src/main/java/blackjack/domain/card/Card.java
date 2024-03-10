package blackjack.domain.card;

public class Card {
    private final Number number;
    private final Shape shape;

    public Card(Number number, Shape shape) {
        this.number = number;
        this.shape = shape;
    }

    public String getSymbol() {
        return number.getSymbol();
    }

    public String getShape() {
        return shape.getValue();
    }

    public int getScore() {
        return number.getScore();
    }

    public boolean isAce() {
        return number.equals(Number.ACE);
    }
}
