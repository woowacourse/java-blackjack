package blackjack.domain.card;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Cards {
    private static final int MAX_SCORE = 21;
    private final List<Card> values;

    public Cards() {
        this.values = new ArrayList<>();
    }

    public void addCard(Card card) {
        values.add(card);
    }

    public List<Card> get() {
        return List.copyOf(values);
    }

    public int totalScore() {
        // TODO: 라인 수 10 이하로 줄이기
        Set<Integer> scoreCases = new HashSet<>();
        calculateScoreCases(scoreCases, values.stream().map(Card::getScores).toList(), 0, 0);

        int maxScore = 0;
        // TODO: 인덴트 1 이하로 줄이기
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

    // TODO: 파라미터 수 3개 이하로 줄이기
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
