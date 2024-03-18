package domain.card;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private static final int STARTING_CARDS_AMOUNT = 2;

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

    public boolean isBust() {
        return totalScore().isBust();
    }

    public boolean isNotBust() {
        return totalScore().isNotBust();
    }

    public boolean isBlackjack() {
        return totalScore().isBlackjackScore() && cards.size() == STARTING_CARDS_AMOUNT;
    }

    public List<Card> getCards() {
        return cards;
    }
}
