package model.card;

public class Card {

    private final CardNumber number;
    private final CardShape shape;

    public Card() {
        this(CardDispenser.generateCardNumber(), CardDispenser.generateCardShape());
    }

    public Card(CardNumber number, CardShape shape) {
        this.number = number;
        this.shape = shape;
    }

    public boolean isAce() {
        return number.isAce(number);
    }

    public String cardTypeLettering() {
        return number.getNumber() + shape.getShape();
    }

    public int score() {
        return number.getValue();
    }
}
