package blackjack.domain;

public class Card {

    private final CardSuit cardSuit;
    private final CardNumber cardNumber;

    public Card(CardSuit cardSuit, CardNumber cardNumber) {
        this.cardSuit = cardSuit;
        this.cardNumber = cardNumber;
    }

    public CardNumber getNumber() {
        return cardNumber;
    }

    public CardSuit getSymbol() {
        return cardSuit;
    }

    public boolean isAce() {
        return cardNumber.isAce();
    }
}
