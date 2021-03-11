package blackjack.domain.card;

public class Card {

    private final CardNumber cardNumber;
    private final CardSymbol cardSymbol;

    public Card(CardNumber number, CardSymbol symbol) {
        this.cardNumber = number;
        this.cardSymbol = symbol;
    }

    public boolean isAce() {
        CardNumber aceNumber = CardNumber.from("A");
        if (cardNumber.equals(aceNumber)) {
            return true;
        }
        return false;
    }

    public int getPoint() {
        return this.cardNumber.getValue();
    }

    public CardNumber getCardNumber() {
        return cardNumber;
    }

    public String getCard() {
        return cardNumber.getAlphabet() + cardSymbol.getSymbol();
    }
}
