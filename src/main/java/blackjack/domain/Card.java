package blackjack.domain;

public class Card {

    private final CardSymbol symbol;
    private final CardNumber number;

    public Card(CardSymbol symbol, CardNumber number) {
        this.symbol = symbol;
        this.number = number;
    }

    public CardSymbol getSymbol() {
        return symbol;
    }

    public CardNumber getNumber() {
        return number;
    }
}
