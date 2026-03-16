package domain;

import java.util.Objects;

import domain.enums.CardRank;
import domain.enums.CardShape;

public class Card {
    private final CardShape cardShape;
    private final CardRank cardRank;

    public Card(CardShape cardShape, CardRank cardRank) {
        this.cardShape = cardShape;
        this.cardRank = cardRank;
    }

    public boolean isAce(){
        return  cardRank.equals(CardRank.ACE);
    }

    public CardRank getCardRank() {
        return cardRank;
    }

    public CardShape getCardShape() {
        return cardShape;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return cardShape == card.cardShape && cardRank == card.cardRank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardShape, cardRank);
    }
}
