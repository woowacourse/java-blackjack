package blackjack.domain.card;

public class Card {

    private static final String ACE_NUMBER = "A";

    private final CardNumber cardNumber;
    private final CardSymbol cardSymbol;

    public Card(CardNumber cardNumber, CardSymbol cardSymbol) {
        this.cardNumber = cardNumber;
        this.cardSymbol = cardSymbol;
    }

    public boolean isAce() {
        return cardNumber == CardNumber.A;
    }

    public int getCardValue() {
        return cardNumber.getValue();
    }

    public String parseToString() {
        return cardNumber.getNumber() + cardSymbol.getSymbol();
    }
}
