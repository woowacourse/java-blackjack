package blackJack.domain.Card;

public class Card {
    private Shape shape;
    private Number number;

    public Card(Shape shape, Number number) {
        this.shape = shape;
        this.number = number;
    }

    public Number getNumber() {
        return number;
    }

    public String getCardInfo() {
        return number.getDenomination() + shape.getShapeName();
    }
}
