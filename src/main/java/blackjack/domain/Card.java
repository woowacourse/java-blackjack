package blackjack.domain;

public class Card {
    private final CardNumber cardNumber;
    private final CardSymbol cardSymbol;

    public Card(CardNumber cardNumber, CardSymbol cardSymbol) {
        this.cardNumber = cardNumber;
        this.cardSymbol = cardSymbol;
    }

    public boolean isACE() {
        return cardNumber == CardNumber.ACE;
    }


    public int getScore() {
        return cardNumber.getScore();
    }

    public String getCardNumberToString() {
        return cardNumber.getNumber();
    }

    public String getCardSymbolToString() {
        return cardSymbol.getSymbol();
    }
}
