package domain;

import domain.dto.CardDto;

public class Card {
    private final CardShape cardShape;
    private final CardRank cardRank;

    public Card(CardShape cardShape, CardRank cardRank) {
        this.cardShape = cardShape;
        this.cardRank = cardRank;
    }

    public CardRank getCardRank() {
        return cardRank;
    }

    public CardShape getCardShape() {
        return cardShape;
    }
}
