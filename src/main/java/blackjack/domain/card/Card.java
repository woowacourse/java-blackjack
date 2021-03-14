package blackjack.domain.card;

public class Card {

    private static final String ACE_NUMBER = "A";

    private final CardNumber cardNumber;
    private final CardSymbol cardSymbol;

    public Card(String number, String symbol) {
        this.cardNumber = CardNumber.matchByNumber(number);
        this.cardSymbol = CardSymbol.matchByInput(symbol);
    }

    public boolean isAce() {
        return cardNumber == CardNumber.A;
    }

    public int getCardValue() {
        return cardNumber.getValue();
    }

    public String getCard() {
        return cardNumber.getNumber() + cardSymbol.getSymbol();
    }
}
