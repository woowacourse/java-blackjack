package model.Deck;

public final class Card {
    private final CardRank cardRank;
    private final CardSuit cardSuit;

    public Card(final CardRank cardRank, final CardSuit cardSuit) {
        this.cardRank = cardRank;
        this.cardSuit = cardSuit;
    }

    public int getCardRankDefaultValue() {
        return cardRank.getDefaultValue();
    }

    public CardRank getCardRank() {
        return cardRank;
    }

    public String getCardName() {
        return cardRank.getName() + cardSuit.getShapeMeaning();
    }
}
