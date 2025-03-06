package model;

public class Card {
    private final CardRank cardRank;
    private final CardSuit cardSuit;

    public Card(CardRank cardRank, CardSuit cardSuit) {
        this.cardRank = cardRank;
        this.cardSuit = cardSuit;
    }

    public int getCardRankValue() {
        return cardRank.getValue();
    }

    public CardRank getCardRank() {
        return cardRank;
    }

    public String getCardName() {
        return cardRank.getName() + cardSuit.getShapeMeaning();
    }
}
