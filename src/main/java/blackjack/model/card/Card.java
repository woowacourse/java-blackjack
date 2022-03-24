package blackjack.model.card;

public class Card {
    private final CardNumber cardNumber;
    private final CardSymbol cardSymbol;

    public Card(CardNumber cardNumber, CardSymbol cardSymbol) {
        this.cardNumber = cardNumber;
        this.cardSymbol = cardSymbol;
    }

    public CardNumber getNumber() {
        return cardNumber;
    }

    public CardSymbol getSymbol() {
        return cardSymbol;
    }
}
