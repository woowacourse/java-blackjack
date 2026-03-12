package domain;

import java.util.List;

public record Hand(List<Card> cards) {

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

    @Override
    public List<Card> cards() {
        return List.copyOf(cards);
    }
}
