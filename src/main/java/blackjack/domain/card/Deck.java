package blackjack.domain.card;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Deck {
    private static final int MAX_SCORE = 21;

    private final List<Card> values;

    public Deck() {
        this.values = new ArrayList<>();
    }

    public void addCard(Card card) {
        values.add(card);
    }

    public List<Card> get() {
        return List.copyOf(values);
    }

    public int totalScore() {
        Set<Integer> scoreCases = new HashSet<>();
        List<List<Integer>> cardsScores = values.stream().map(card -> card.getScore().get()).toList();
        calculateScoreCases(scoreCases, cardsScores, 0, 0);

        return scoreCases.stream()
                .filter(score -> score <= MAX_SCORE)
                .max(Integer::compare)
                .orElse(scoreCases.stream().min(Integer::compare).get());
    }

    private void calculateScoreCases(Set<Integer> scoreCases, List<List<Integer>> cardsScores, int sum, int index) {
        if (index == cardsScores.size()) {
            scoreCases.add(sum);
            return;
        }
        for (int score : cardsScores.get(index)) {
            calculateScoreCases(scoreCases, cardsScores, sum + score, index + 1);
        }
    }
}
