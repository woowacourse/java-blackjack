package domain.card;

public class Card {
    private CardSuit cardSuit;
    private CardNumber cardNumber;

    public Card(CardSuit cardSuit, CardNumber cardNumber) {
        this.cardSuit = cardSuit;
        this.cardNumber = cardNumber;
    }

    @Override
    public String toString() {
        return cardNumber.getScore() + cardSuit.getSuit();
    }
}
