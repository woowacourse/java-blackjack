package blackjack.domain;

public class Card {

    private final CardNumber number;
    private final CardSymbol symbol;

    public Card(CardNumber number, CardSymbol symbol) {
        this.number = number;
        this.symbol = symbol;
    }
}


