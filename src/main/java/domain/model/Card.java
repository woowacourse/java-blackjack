package domain.model;

public class Card {
    private final CardRank cardRank;
    private final CardShape cardShape;

    private Card(CardRank cardRank, CardShape cardShape) {
        this.cardRank = cardRank;
        this.cardShape = cardShape;
    }

    public static Card of(CardRank cardRank, CardShape cardShape) {



        return new Card(cardRank, cardShape);
    }

    public CardRank getCardRank() {
        return cardRank;
    }

    public CardShape getCardShape() {
        return cardShape;
    }

    public boolean isSameCard(Card card) {
        if (card.cardRank == this.cardRank && card.cardShape == this.cardShape) {
            return true;
        }
        return false;
    }

    public int getValue() {
        return cardRank.getValue();
    }

    public boolean isAce() {
        return cardRank == CardRank.ACE;
    }
}
