package model.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Hand {
    private final List<Card> cards;
    private int score;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public void addCards(List<Card> cards) {
        this.cards.addAll(cards);
    }

    public void calculateScore(boolean isDealer) {
        if (isDealer) {
            calculateDealerScore();
            return;
        }
        calculatePlayerScore();
    }

    private void calculateDealerScore() {
        int score = 0;
        for (Card card : cards) {
            score += card.getRankValue().getLast();
        }
        this.score = score;
    }

    private void calculatePlayerScore() {
        Set<Integer> possibleScores = calculatePossibleScores();
        this.score = possibleScores.stream()
                .filter(value -> value <= 21)
                .max(Integer::compareTo)
                .orElse(Collections.min(possibleScores));
    }

    private Set<Integer> calculatePossibleScores() {
        List<Integer> scores = new ArrayList<>(List.of(0));
        for (Card card : cards) {
            scores = scores.stream()
                    .flatMap(score -> card.getRankValue().stream().map(value -> score + value))
                    .toList();
        }
        return new HashSet<>(scores);
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getScore() {
        return score;
    }
}
