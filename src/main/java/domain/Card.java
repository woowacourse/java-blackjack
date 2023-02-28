package domain;

public class Card {
    private final CardSuit cardSuit;
    private final CardNumber cardNumber;

    public Card(CardSuit cardSuit, CardNumber cardNumber) {
        this.cardSuit = cardSuit;
        this.cardNumber = cardNumber;
    }

    public CardSuit getSuit() {
        return cardSuit;
    }

    public CardNumber getNumber() {
        return cardNumber;
    }
}
