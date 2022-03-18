package blackjack.model.card;

public class Card {
    private final CardNumber cardNumber;
    private final Symbol symbol;

    public Card(CardNumber cardNumber, Symbol symbol) {
        this.cardNumber = cardNumber;
        this.symbol = symbol;
    }

    public CardNumber getNumber() {
        return cardNumber;
    }

    public String getNumberAndSymbol() {
        return cardNumber.getValueForPrint() + symbol.getValue();
    }
}
