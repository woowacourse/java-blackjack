package domain.participant;

import domain.card.Card;
import domain.result.Score;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public record Hand(List<Card> cards) {
    public Hand() {
        this(new ArrayList<>());
    }

    public Hand(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public void add(Card card) {
        cards.add(card);
    }

    public Score calculateScore() {
        int totalScore = calculateTotalScore();
        int aceCount = countAce();
        totalScore = adjustAceScore(totalScore, aceCount);
        return new Score(totalScore);
    }

    private int adjustAceScore(int totalScore, int aceCount) {
        while (canLowerAceScore(totalScore, aceCount)) {
            totalScore -= 10;
            aceCount--;
        }
        return totalScore;
    }

    private int countAce() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    private int calculateTotalScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    public int size() {
        return cards.size();
    }

    private boolean canLowerAceScore(int score, int aceCount) {
        return score > 21 && aceCount > 0;
    }

    @Override
    public List<Card> cards() {
        return Collections.unmodifiableList(cards);
    }
}
