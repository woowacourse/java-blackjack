package domain;

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
}
