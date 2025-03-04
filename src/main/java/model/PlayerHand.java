package model;

import java.util.List;

public class PlayerHand {
    private final List<Card> cards;

    public PlayerHand(List<Card> cards) {
        this.cards = cards;
    }

    public int calculateScoreSum() {
        return cards.stream()
                .mapToInt(card -> card.getCardRankValue())
                .sum();
    }
}
