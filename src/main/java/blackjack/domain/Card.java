package blackjack.domain;

import java.util.Objects;

class Card {

    private final CardRank cardRank;
    private final CardShape cardShape;

    public Card(CardRank cardRank, CardShape cardShape) {
        this.cardRank = cardRank;
        this.cardShape = cardShape;
    }

    public CardRank getCardRank() {
        return cardRank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return cardRank == card.cardRank && cardShape == card.cardShape;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardRank, cardShape);
    }
}
