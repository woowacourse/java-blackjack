package blackjack.domain;

public class Card {
    private final CardNumber cardNumber;
    private final CardType cardType;

    public Card(final CardNumber cardNumber, final CardType cardType) {
        this.cardNumber = cardNumber;
        this.cardType = cardType;
    }

    public boolean isAce() {
        return cardNumber == CardNumber.ACE;
    }

    public String getCardSymbol() {
        return cardNumber.getSymbol();
    }

    public int getCardNumber() {
        return cardNumber.getValue();
    }

    public String getCardType() {
        return cardType.getType();
    }

    public String getCard() {
        return getCardSymbol() + getCardType();
    }
}
