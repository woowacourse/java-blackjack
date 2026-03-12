package domain;

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return cardShape == card.cardShape && cardContents == card.cardContents;
    }

    public CardShape getCardShape() {
        return cardShape;
    }

    public CardContents getCardContents() {
        return cardContents;
    }
}
