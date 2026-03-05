package domain;

public class Card {
    private final CardShape cardShape;
    private final CardRank cardRank;

    public Card(CardShape cardShape, CardRank cardRank) {
        this.cardShape = cardShape;
        this.cardRank = cardRank;
    }
}
