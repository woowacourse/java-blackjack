package blackjack.domain.card;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Hand {
    private static final int MAX_SCORE = 21;

    private final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void addCards(List<Card> cards) {
        this.cards.addAll(cards);
    }

    public Card getCard(int index) {
        return cards.get(index);
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public int getSize() {
        return cards.size();
    }

    public boolean isBlackjackScore() {
        return totalScore() == MAX_SCORE;
    }

    public boolean isBustScore() {
        return totalScore() > MAX_SCORE;
    }

    public boolean isUnderBoundScore() {
        return totalScore() < MAX_SCORE;
    }

    public int totalScore() {
        Set<Integer> scoreCases = generateScoreCases();

        return scoreCases.stream()
                .filter(score -> score <= MAX_SCORE)
                .max(Integer::compare)
                .orElse(minScore(scoreCases));
    }

    private Set<Integer> generateScoreCases() {
        return new HashSet<>(getCardsScores().stream()
                .reduce(List.of(0), this::combinationSumOfScores));
    }

    private List<List<Integer>> getCardsScores() {
        return cards.stream()
                .map(Card::getScore)
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
