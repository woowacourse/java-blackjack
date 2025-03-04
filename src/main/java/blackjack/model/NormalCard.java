package blackjack.model;

public class NormalCard {
    private final int number;
    private final CardShape shape;

    public NormalCard(int number, CardShape shape) {
        this.number = number;
        this.shape = shape;
    }

    public int getNumber() {
        return number;
    }

    public CardShape getShape() {
        return shape;
    }
}
