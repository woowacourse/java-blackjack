public class Card {

    private final Shape shape;
    private final Number number;

    public Card(Shape shape, Number number) {
        this.shape = shape;
        this.number = number;
    }

    public int getScore() {
        return number.getScore();
    }
}
