package blackjack.domain.card;

public class Card {
    private final CardNumber cardNumber;
    private final CardType cardType;

    public Card(final CardNumber cardNumber, final CardType cardType) {
        this.cardNumber = cardNumber;
        this.cardType = cardType;
    }

    public boolean isAce() {
        return (this.cardNumber == CardNumber.ACE);
    }

    public int getCardValue() {
        return cardNumber.getValue();
    }

    public String getCard() {
        return getCardSymbol() + getCardType();
    }

    public String getCardSymbol() {
        return cardNumber.getSymbol();
    }

    public String getCardType() {
        return cardType.getType();
    }
}
