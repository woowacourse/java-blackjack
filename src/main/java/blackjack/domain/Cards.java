package blackjack.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Cards {
    private static final int MAX_SCORE = 21;
    private final List<Card> values;

    public Cards(List<Card> values) {
        this.values = new ArrayList<>(values);
    }

    public List<Card> getValues() {
        return List.copyOf(values);
    }

    public int totalScore() {
        Set<Integer> scoreCases = new HashSet<>();
        calculateScoreCases(scoreCases, values.stream().map(Card::getScores).toList(), 0, 0);

        int maxScore = 0;
        for (int scoreCase : scoreCases) {
            if (scoreCase > MAX_SCORE) {
                continue;
            }

            if (scoreCase > maxScore) {
                maxScore = scoreCase;
            }
        }
        if (maxScore == 0) {
            return scoreCases.stream().toList().get(0);
        }
        return maxScore;
    }

    public int getCardsCount() {
        return values.size();
    }

    private void calculateScoreCases(Set<Integer> scoreCases, List<List<Integer>> scores, int sum, int index) {
        if (index == scores.size()) {
            scoreCases.add(sum);
            return;
        }
        for (int score : scores.get(index)) {
            calculateScoreCases(scoreCases, scores, sum + score, index + 1);
        }
    }
}
