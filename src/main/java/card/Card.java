package card;

public class Card {

    private final CardType cardType;
    private final CardNumber cardNumber;

    public Card(CardType cardType, CardNumber cardNumber) {
        this.cardType = cardType;
        this.cardNumber = cardNumber;
    }

    public boolean isAce() {
        return cardNumber.equals(CardNumber.ACE);
    }

    public CardNumber getCardNumber() {
        return cardNumber;
    }

    public CardType getCardType() {
        return cardType;
    }
}
