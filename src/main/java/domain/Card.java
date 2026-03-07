package domain;

import java.util.Objects;

public class Card {
    private final CardShape cardShape;
    private final CardContents cardContents;

    public Card(CardShape cardShape, CardContents cardContents) {
        this.cardShape = cardShape;
        this.cardContents = cardContents;
    }

    public CardContents getCardContents() {
        return cardContents;
    }

    public boolean isAce() {
        return cardContents.getNumber().equals("A");
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return cardShape == card.cardShape && cardContents == card.cardContents;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardShape, cardContents);
    }

    @Override
    public String toString() {
        return cardContents.getNumber() + cardShape.name();
    }
}
