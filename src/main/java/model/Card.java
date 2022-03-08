package model;

public class Card {
    private final CardSuit cardSuit;
    private final CardFace cardFace;

    public Card(CardSuit cardSuit, CardFace cardFace) {
        this.cardSuit = cardSuit;
        this.cardFace = cardFace;
    }

    public int getCardScore() {
        return cardFace.getCardScore();
    }

    public boolean isAceCard() {
        return this.cardFace == CardFace.ACE;
    }
}
