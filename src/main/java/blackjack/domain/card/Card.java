package blackjack.domain.card;

public class Card {
    private final Number number;
    private final Shape shape;

    public Card(Number number, Shape shape) {
        this.number = number;
        this.shape = shape;
    }

    public boolean isAce() {
        return number.equals(Number.ACE);
    }

    public String getSignature() {
        return number.getSymbol() + shape.getValue();
    }

    public int getScore() {
        return number.getScore();
    }
}
