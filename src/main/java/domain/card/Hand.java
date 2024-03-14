package domain.card;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public void add(Card addedCard) {
        cards.add(addedCard);
    }

    public void add(List<Card> addedCards) {
        cards.addAll(addedCards);
    }

    public Score totalScore() {
        return Score.totalScoreOf(this);
    }

    public boolean hasAce() {
        return cards.stream()
            .anyMatch(card -> card.rank() == Rank.ACE);
    }

    public boolean isBlackjack() {
        return totalScore().isBlackjackScore() && cards.size() == 2;
    }

    public List<Card> getCards() {
        return cards;
    }
}
