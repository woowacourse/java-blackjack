package domain;

import java.util.List;

public class Card {
    private final CardSuit cardSuit;
    private final CardRank cardRank;

    public Card(CardSuit cardSuit, CardRank cardRank) {
        this.cardSuit = cardSuit;
        this.cardRank = cardRank;
    }

    public List<Integer> getScores() {
        return cardRank.getScores();
    }

    public CardRank getCardNumber() {
        return cardRank;
    }

    public CardSuit getCardShape() {
        return cardSuit;
    }
}
