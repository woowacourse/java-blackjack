package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParticipantHand {
    private final List<Card> cards;

    public ParticipantHand(){
        this.cards = new ArrayList<>();
    }
    public ParticipantHand(List<Card> cards) {
        this.cards = cards;
    }

    public int calculateScoreSum() {
        //TODO : ace에 대한 상황을 덜 고려함
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
        int score = calculateScoreSum();
        for (Card card : cards) {
            if (card.getCardRank() == CardRank.ACE) {
                score+=1;
            }
        }
        return score > 21;
    }
}
