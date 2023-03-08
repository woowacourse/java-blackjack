package blackjack.domain;

public class Card {
    private final CardNumber cardNumber;
    private final CardSymbol cardSymbol;

    public Card(CardNumber cardNumber, CardSymbol cardSymbol) {
        this.cardNumber = cardNumber;
        this.cardSymbol = cardSymbol;
    }

    public boolean isACE() {
        return cardNumber.number().equals(CardNumber.ACE.number());
    }

    public int getScore(String number) {
        return CardNumber.scoreByNumber(number);
    }

    public String getCardSymbolToString() {
        return cardSymbol.symbol();
    }

    public String getCardNumberToString() {
        return cardNumber.number();
    }
}
