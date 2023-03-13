package domain;

public class Card {
    private final CardType cardType;
    private final CardValue cardValue;

    public Card(CardType cardType, CardValue cardValue) {
        this.cardType = cardType;
        this.cardValue = cardValue;
    }

    public CardValue getCardValue() {
        return cardValue;
    }

    public CardType getCardType() {
        return cardType;
    }
}
