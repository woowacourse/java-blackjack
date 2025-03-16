package model.deck;

public final class Card {
    private final CardRank cardRank;
    private final CardSuit cardSuit;

    public Card(final CardRank cardRank, final CardSuit cardSuit) {
        this.cardRank = cardRank;
        this.cardSuit = cardSuit;
    }

    public boolean isSoftCard() {
        return this.cardRank.hasMaxValue();
    }

    public int getCardRankDefaultValue() {
        return cardRank.getDefaultValue();
    }

    public String getCardName() {
        return cardRank.getName() + cardSuit.getShapeMeaning();
    }
}
