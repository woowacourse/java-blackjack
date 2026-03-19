package domain.gameplaying;

import java.util.ArrayList;
import java.util.List;

class Hand {

    private static final int BUST_THRESHOLD = 21;
    private static final int ACE_WEIGHT = 10;

    private final DrawStrategy drawStrategy;
    private final List<Card> cards;

    Hand(DrawStrategy drawStrategy, List<Card> cards) {
        this.drawStrategy = drawStrategy;
        this.cards = new ArrayList<>(cards);
    }

    static Hand using(DrawStrategy deck) {
        return new Hand(deck, new ArrayList<>());
    }

    void drawCard() {
        cards.add(drawStrategy.draw());
    }

    List<Card> cards() {
        return List.copyOf(cards);
    }

    boolean isBusted() {
        int score = rawScoreSum();
        if (aceCount() > 0) {
            score -= aceCount() * ACE_WEIGHT;
        }
        return score > BUST_THRESHOLD;
    }

    int scoreSum() {
        int total = rawScoreSum();
        int aces = aceCount();

        while (isExceededBustNumber(total) && aces > 0) {
            total -= ACE_WEIGHT;
            aces--;
        }

        return total;
    }

    private int rawScoreSum() {
        return cards.stream()
                .mapToInt(Card::score)
                .sum();
    }

    private int aceCount() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    private boolean isExceededBustNumber(int total) {
        return total > BUST_THRESHOLD;
    }
}
