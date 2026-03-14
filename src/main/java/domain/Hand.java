package domain;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private final List<Card> cards;

    public Hand(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public Score getScore() {
        return new Score(calculateScore());
    }

    private int calculateScore() {
        return ScoreCalculator.calculate(List.copyOf(cards));
    }

    public boolean isBlackjack() {
        return getScore().isBlackjackScore() && cards.size() == 2;
    }

    public boolean isBust() {
        return getScore().isBust();
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
