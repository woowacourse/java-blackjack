public class Card {

    private final CardNumber cardNumber;
    private final CardShape cardShape;

    public Card(CardNumber cardNumber, CardShape cardShape) {
        this.cardNumber = cardNumber;
        this.cardShape = cardShape;
    }

    public boolean isAce() {
        return cardNumber.equals(CardNumber.A);
    }

    public boolean isNotAce() {
        return !cardNumber.equals(CardNumber.A);
    }

    public int score() {
        return cardNumber.getScore();
    }

    public String getName() {
        return cardNumber.name() + cardShape.name();

    }
}
