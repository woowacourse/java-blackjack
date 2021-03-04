package blackjack.domain.card;

public class Card {
    private final CardType cardType;
    private final CardValue cardValue;

    public Card(CardType cardType, CardValue cardValue) {
        this.cardType = cardType;
        this.cardValue = cardValue;
    }

    public boolean isAce() {
        return this.cardValue == CardValue.ACE;
    }

    public int getPoint() {
        return cardValue.getPoint();
    }

    public CardType getCardType() {
        return cardType;
    }

    public CardValue getCardValue() {
        return cardValue;
    }
}
