package blackjack.domain.card;

public class Card {

    private final Shape shape;
    private final Number number;

    public Card(Shape shape, Number number) {
        this.shape = shape;
        this.number = number;
    }

    public Shape getShape() {
        return shape;
    }

    // TODO : string, number 어떤걸 get 해야할까?
    public Number getNumber() {
        return number;
    }

    public int getPoint() {
        // TODO : Ace의 경우 1 또는 11 반환 처리
        return number.getValue();
    }
}
