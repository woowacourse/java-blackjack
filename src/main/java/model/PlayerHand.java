package model;

import java.util.ArrayList;
import java.util.List;

public class PlayerHand {
    private final List<Card> cards;

    public PlayerHand(){
        this.cards = new ArrayList<>();
    }
    public PlayerHand(List<Card> cards) {
        this.cards = cards;
    }

    public int calculateScoreSum() {
        return cards.stream()
                .mapToInt(card -> card.getCardRankValue())
                .sum();
    }

    public void add(Card card) {
        cards.add(card);
    }
}
