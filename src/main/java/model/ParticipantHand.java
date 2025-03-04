package model;

import java.util.ArrayList;
import java.util.List;

public class ParticipantHand {
    private final List<Card> cards;

    public ParticipantHand(){
        this.cards = new ArrayList<>();
    }
    public ParticipantHand(List<Card> cards) {
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

    public List<Card> getCards() {
        return cards;
    }

    public boolean checkBurst() {
        return calculateScoreSum() > 21;
    }
}
