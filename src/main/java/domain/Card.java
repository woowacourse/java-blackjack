package domain;

public class Card {
    private final Symbol symbol;
    private final CardNumber cardNumber;

    public Card(Symbol symbol, CardNumber cardNumber) {
        this.symbol = symbol;
        this.cardNumber = cardNumber;
    }

    public int getScore() {
        return this.cardNumber.getScore();
    }

    public String getSymbol() {
        return this.symbol.getName();
    }

    public String getCardNumber() {
        return this.cardNumber.getNumber();
    }

    public boolean isAce() {
        return this.cardNumber.isAce();
    }

    public boolean isTenCard() {
        return this.cardNumber.isTenCard();
    }
}
