package model.card;

public class Card {
    private final CardRank cardRank;
    private final CardSuit cardSuit;

    public Card(CardRank cardRank, CardSuit cardSuit) {
        this.cardRank = cardRank;
        this.cardSuit = cardSuit;
    }

    public int getCardRankDefaultValue() {
        return cardRank.getDefaultValue();
    }

    public boolean isAce(){
        return cardRank == CardRank.ACE;
    }

    public String getCardName() {
        return cardRank.getName() + cardSuit.getShapeMeaning();
    }
}
