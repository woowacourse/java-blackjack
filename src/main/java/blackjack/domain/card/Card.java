package blackjack.domain.card;

public class Card {

    private CardNumber cardNumber;
    private CardSymbol cardSymbol;

    public Card(CardNumber number, CardSymbol symbol) {
        this.cardNumber = number;
        this.cardSymbol = symbol;
    }

    public int getPoint() {
        return this.cardNumber.getValue();
    }

}
