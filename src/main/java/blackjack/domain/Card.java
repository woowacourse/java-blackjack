package blackjack.domain;

import java.util.List;

public class Card {
    private final CardSymbol cardSymbol;
    private final CardNumber cardNumber;

    public Card(CardNumber cardNumber, CardSymbol cardSymbol) {
        this.cardNumber = cardNumber;
        this.cardSymbol = cardSymbol;
    }

    public List<Integer> getScore() {
        return cardNumber.getScore();
    }

    public String getCardSymbolToString() {
        return cardSymbol.getSymbol();
    }

    public String getCardNumberToString() {
        return cardNumber.getNumber();
    }
}
