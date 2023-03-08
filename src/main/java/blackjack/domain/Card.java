package blackjack.domain;

public class Card {
    private final CardSymbol cardSymbol;
    private final CardNumber cardNumber;

    public Card(CardNumber cardNumber, CardSymbol cardSymbol) {
        this.cardNumber = cardNumber;
        this.cardSymbol = cardSymbol;
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
