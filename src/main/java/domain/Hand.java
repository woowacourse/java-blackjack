package domain;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private static final int MAX_PLAYABLE_NUMBER = 21;
    private static final int ACE_WEIGHT = 10;

    private final DrawStrategy drawStrategy;
    private final List<Card> cards;

    public Hand(DrawStrategy drawStrategy, List<Card> cards) {
        this.drawStrategy = drawStrategy;
        this.cards = new ArrayList<>(cards);
    }

    void drawCard() {
        cards.add(createCard());
    }

    public boolean isBlackJack() {
        int initCardSum = cards.getFirst().score() + cards.get(1).score();
        return initCardSum == MAX_PLAYABLE_NUMBER;
    }

    public List<Card> cards() {
        return List.copyOf(cards);
    }

    public boolean isBusted() {
        int score = rawScoreSum();
        if (aceCount() > 0) {
            score -= aceCount() * ACE_WEIGHT;
        }
        return score > MAX_PLAYABLE_NUMBER;
    }

    public int scoreSum() {
        int total = rawScoreSum();
        if (isOvercome()) {
            total -= aceCount() * ACE_WEIGHT;
        }
        return total;
    }

    private int rawScoreSum() {
        return cards.stream().mapToInt(Card::score).sum();
    }

    private int aceCount() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    private Card createCard() {
        return drawStrategy.draw();
    }

    private boolean isOvercome() {
        return rawScoreSum() > MAX_PLAYABLE_NUMBER;
    }
}
