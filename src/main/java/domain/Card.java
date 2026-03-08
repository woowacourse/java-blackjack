package domain;

public record Card(Suit suit, CardNumber cardNumber) {
    public boolean isAce() {
        return this.cardNumber == CardNumber.ACE;
    }

}
