package domain.card;

public class Card {
    private final CardNumber cardNumber;
    private final CardSymbol cardSymbol;

    public Card(final CardNumber cardNumber, final CardSymbol cardSymbol) {
        this.cardNumber = cardNumber;
        this.cardSymbol = cardSymbol;
    }

    public int getNumber() {
        return cardNumber.getNumber();
    }

    public boolean isAceCard() {
        return cardNumber == CardNumber.ACE;
    }
}
