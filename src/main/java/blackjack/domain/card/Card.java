package blackjack.domain.card;

public class Card {

    private final CardNumber number;
    private final CardSymbol symbol;

    public Card(CardNumber number, CardSymbol symbol) {
        this.number = number;
        this.symbol = symbol;
    }

    public CardNumber getNumber(){
        return this.number;
    }

    public CardSymbol getSymbol(){
        return this.symbol;
    }

    public boolean isAce(){
        return number.equals(CardNumber.ACE);
    }
}
