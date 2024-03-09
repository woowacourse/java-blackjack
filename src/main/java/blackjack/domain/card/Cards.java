package blackjack.domain.card;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        Set<Integer> scoreCases = generateScoreCases();

        return scoreCases.stream()
                .filter(score -> score <= MAX_SCORE)
                .max(Integer::compare)
                .orElse(minScore(scoreCases));
    }

    Set<Integer> generateScoreCases() {
        return new HashSet<>(getCardsScores().stream()
                .reduce(List.of(0), this::combinationSumOfScores));
    }

    private List<List<Integer>> getCardsScores() {
        return values.stream()
                .map(card -> card
                        .getScore()
                        .get())
                .toList();
    }

    private List<Integer> combinationSumOfScores(List<Integer> scores1, List<Integer> scores2) {
        return scores1.stream()
                .flatMap(score1 -> scores2.stream()
                        .map(score2 -> score1 + score2))
                .toList();
    }

    private Integer minScore(Set<Integer> scores) {
        return scores.stream()
                .min(Integer::compare)
                .orElse(0);
    }
}
