package domain.card;

public class Card {

    private final CardName cardName;
    private final CardType cardType;

    Card(CardName cardName, CardType cardType) {
        this.cardName = cardName;
        this.cardType = cardType;
    }

    public CardName cardName() {
        return cardName;
    }

    public CardType cardType() {
        return cardType;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardName=" + cardName +
                ", cardType=" + cardType +
                '}';
    }
}
