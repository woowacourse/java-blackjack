package blackjack.domain;

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
}
