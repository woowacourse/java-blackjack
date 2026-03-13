package domain;

import java.util.Objects;

public class Card {
    private final CardShape cardShape;
    private final CardContents cardContents;

    public Card(CardShape cardShape, CardContents cardContents) {
        this.cardShape = cardShape;
        this.cardContents = cardContents;
    }

    public boolean isAce() {
        return cardContents.equals(CardContents.A);
    }

    public String getCardShapeName() {
        return cardShape.getName();
    }

    public String getCardNumber() {
        return cardContents.getNumber();
    }

    public int getCardScore() {
        return cardContents.getScore();
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
        return cardShape == card.cardShape && cardContents == card.cardContents;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardShape, cardContents);
    }
}
