package blackjack.domain;

public class Card {
    private final CardNumber cardNumber;
    private final CardSymbol cardSymbol;

    public Card(CardNumber cardNumber, CardSymbol cardSymbol) {
        this.cardNumber = cardNumber;
        this.cardSymbol = cardSymbol;
    }

    public boolean isACE() {
        return cardNumber.getNumber().equals(CardNumber.ACE.getNumber());
    }

    public int getScore() {
        return CardNumber.scoreByNumber(cardNumber.getNumber());
    }

    public String getCardNumberToString() {
        return cardNumber.getNumber();
    }

    public String getCardSymbolToString() {
        return cardSymbol.getSymbol();
    }
}
