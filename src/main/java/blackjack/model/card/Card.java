package blackjack.model.card;

import blackjack.model.game.CardGroup;

public class Card {
    private final CardShape shape;
    private final CardType cardType;

    public Card(CardShape shape, CardType cardType) {
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

    public boolean isSpecialCard() {
        return getCardType().getCardGroup() == CardGroup.SPECIAL;
    }

    public boolean isAceCard() {
        return getCardType().getCardGroup() == CardGroup.ACE;
    }
}
