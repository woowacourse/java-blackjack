package domain;

public record Card(Suit suit, CardNumber cardNumber) {
    public boolean isAce() {
        return this.cardNumber == CardNumber.ACE;
    }

    public String suitValue() {
        return this.suit.getValue();
    }

    public String symbol() {
        return this.cardNumber.getSymbol();
    }

}
