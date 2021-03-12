package blackjack.domain.card;

import java.util.Objects;

public class Card {
    private final CardLetter cardLetter;
    private final CardSuit cardSuit;

    public Card(final CardLetter cardLetter, final CardSuit cardSuit) {
        this.cardLetter = cardLetter;
        this.cardSuit = cardSuit;
    }

    public boolean isAce() {
        return cardLetter.isAce();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return cardLetter == card.cardLetter &&
                cardSuit == card.cardSuit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardLetter, cardSuit);
    }

    public CardLetter getCardLetter() {
        return this.cardLetter;
    }

    public CardSuit getCardSuit() {
        return this.cardSuit;
    }
}
