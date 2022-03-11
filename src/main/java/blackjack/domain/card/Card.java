package blackjack.domain.card;

public class Card {
    private final CardNumber cardNumber;
    private final CardSymbol cardSymbol;

    public Card(CardNumber cardNumber, CardSymbol cardSymbol) {
        this.cardNumber = cardNumber;
        this.cardSymbol = cardSymbol;
    }

    public int getCardNumberValue() {
        return cardNumber.getCardNumberValue();
    }

    @Override
    public String toString() {
        return cardNumber.toString() + cardSymbol.toString();
    }
}
