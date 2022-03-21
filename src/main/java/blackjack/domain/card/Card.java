package blackjack.domain.card;

import java.util.Objects;

public class Card {
    private final CardDenomination cardDenomination;
    private final CardSuit cardSuit;

    public Card(CardDenomination cardDenomination, CardSuit cardSuit) {
        this.cardDenomination = cardDenomination;
        this.cardSuit = cardSuit;
    }

    public int getScore() {
        return cardDenomination.getScore();
    }

    public String getCardName() {
        return cardDenomination.getName();
    }

    public CardSuit getType() {
        return cardSuit;
    }

    public boolean isAce() {
        return cardDenomination == CardDenomination.ACE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return cardDenomination == card.cardDenomination && cardSuit == card.cardSuit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardDenomination, cardSuit);
    }
}
