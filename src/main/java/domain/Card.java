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

    public Symbol getSymbol() {
        return this.symbol;
    }

    public CardNumber getCardNumber() {
        return this.cardNumber;
    }

    public boolean isAce() {
        return this.cardNumber.isAce();
    }

    public boolean isTenScoreCard() {
        return this.cardNumber.isTenScoreCard();
    }
}
