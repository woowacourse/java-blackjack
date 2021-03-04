package blackjack.domain.card;

public class Card {
    private final GameNumber gameNumber;
    private final Shape shape;

    public Card(GameNumber gameNumber, Shape shape) {
        this.gameNumber = gameNumber;
        this.shape = shape;
    }

    public boolean isAce() {
        return GameNumber.ACE.equals(gameNumber);
    }

    public int getValue() {
        return gameNumber.getValue();
    }

    public String getNumber() {
        return gameNumber.getNumber();
    }

    public String getShape() {
        return shape.getName();
    }
}
