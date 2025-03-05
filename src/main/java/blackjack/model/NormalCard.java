package blackjack.model;

public class NormalCard extends Card {
    private final int number;

    public NormalCard(int number, CardShape shape) {
        super(shape);
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public int getPoint() {
        return number;
    }
}
