package blackjack.model.card;

public class Card {
    private final CardShape shape;
    private final CardType cardType;

    public Card(final CardShape shape, final CardType cardType) {
        this.shape = shape;
        this.cardType = cardType;
    }

    public boolean isAceCard() {
        return this.cardType.isAce();
    }

    public int getPoint() {
        return this.cardType.getPoint();
    }

    public CardShape getShape() {
        return this.shape;
    }

    public CardType getCardType() {
        return this.cardType;
    }
}
