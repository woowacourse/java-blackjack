package blackjack.domain.card;

public class Card {

    private final CardNumber cardNumber;
    private final CardSymbol symbol;

    public Card(CardNumber CardNumber, CardSymbol symbol) {
        this.cardNumber = CardNumber;
        this.symbol = symbol;
    }

    public CardNumber getCardNumber() {
        return this.cardNumber;
    }

    public String getSymbol() {
        return this.symbol.getSymbol();
    }

    public boolean isAce() {
        return cardNumber.equals(CardNumber.ACE);
    }
}
