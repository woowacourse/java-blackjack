package blackjack.model;

public class Card {
    private final CardShape shape;
    private final CardType cardType;

    protected Card(CardShape shape, CardType cardType) {
        this.shape = shape;
        this.cardType = cardType;
    }

    public CardShape getShape() {
        return shape;
    }

    public int getPoint() {
        return cardType.getPoint();
    }

    public CardType getCardType() {
        return cardType;
    }

    public boolean equalsCardType(CardType cardType) {
        return this.cardType.equals(cardType);
    }
}
