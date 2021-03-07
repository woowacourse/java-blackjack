package blackjack.domain.card;

public class Card {
    private final CardLetter cardLetter;
    private final CardSuit cardSuit;

    public Card(final CardLetter cardLetter, final CardSuit cardSuit) {
        this.cardLetter = cardLetter;
        this.cardSuit = cardSuit;
    }

    public boolean isAce() {
        return this.cardLetter == CardLetter.ACE;
    }

    public CardLetter getCardLetter() {
        return this.cardLetter;
    }

    public CardSuit getCardSuit() {
        return this.cardSuit;
    }
}
