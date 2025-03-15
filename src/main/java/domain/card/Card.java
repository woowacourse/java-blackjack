package domain.card;

public class Card {
    private final CardNumber cardNumber;
    private final CardSymbol cardSymbol;

    public Card(final CardNumber cardNumber, final CardSymbol cardSymbol) {
        this.cardNumber = cardNumber;
        this.cardSymbol = cardSymbol;
    }

    public boolean isAceCard() {
        return cardNumber == CardNumber.ACE;
    }

    public int getNumber() {
        return cardNumber.getNumber();
    }

    public String getName() {
        return cardNumber.getName();
    }

    public String getCardSymbol() {
        return cardSymbol.getName();
    }
}
