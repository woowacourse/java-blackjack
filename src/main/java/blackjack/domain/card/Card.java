package blackjack.domain.card;

public final class Card {
    private final CardSymbol cardSymbol;
    private final CardNumber cardNumber;

    public Card(CardNumber cardNumber, CardSymbol cardSymbol) {
        this.cardNumber = cardNumber;
        this.cardSymbol = cardSymbol;
    }

    public int getScore() {
        return cardNumber.getScore();
    }

    public String getCardSymbolToString() {
        return cardSymbol.getSymbol();
    }

    public String getCardNumberToString() {
        return cardNumber.getNumber();
    }

    public boolean isAce() {
        return this.cardNumber.equals(CardNumber.ACE);
    }
}
