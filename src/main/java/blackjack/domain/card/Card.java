package blackjack.domain.card;

public class Card {
    private final CardNumber cardNumber;
    private final CardSuit cardSuit;

    public Card(final CardNumber cardNumber, final CardSuit cardSuit) {
        this.cardNumber = cardNumber;
        this.cardSuit = cardSuit;
    }

    public boolean isAce() {
        return this.cardNumber == CardNumber.ACE;
    }

    public CardNumber getCardNumber() {
        return this.cardNumber;
    }

    public CardSuit getCardSuit() {
        return this.cardSuit;
    }
}
