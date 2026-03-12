package domain.card;

public record Card(Suit suit, Rank cardNumber) {
    public boolean isAce() {
        return this.cardNumber == Rank.ACE;
    }

    public String symbol() {
        return this.cardNumber.getSymbol();
    }
}
