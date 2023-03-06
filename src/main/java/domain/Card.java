package domain;

public class Card {
    private final Shape shape;
    private final CardRank number;

    public Card(Shape shape, CardRank number) {
        this.shape = shape;
        this.number = number;
    }

    public boolean isAce() {
        return this.number == CardRank.ACE;
    }

    public int getScore() {
        return number.getScore();
    }

    public String getShapeName() {
        return shape.getName();
    }

    public String getNumberName() {
        return number.getName();
    }
}
