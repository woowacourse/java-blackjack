package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Participant {

    private final List<Card> cards = new ArrayList<>();
    private final Map<Result, Integer> result = new HashMap<>();

    public Participant() {
        Arrays.stream(Result.values())
                .forEach(result -> this.result.put(result, 0));
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public int calculateScore() {
        int score = cards.stream()
                .filter(card -> !card.getRank().equals(Rank.ACE))
                .mapToInt(Card::getRankScore)
                .sum();

        boolean aceExist = cards.stream()
                .anyMatch(card -> card.getRank().equals(Rank.ACE));

        if (aceExist) {
            return (score + calculateAceScore(21 - score));
        }

        return score;
    }

    private int calculateAceScore(int remainScore) {
        int minAceScore = cards.stream()
                .filter(card -> card.getRank().equals(Rank.ACE))
                .mapToInt(Card::getRankScore)
                .sum();

        if (remainScore < minAceScore + 10) {
            return minAceScore;
        }

        return minAceScore + 10;
    }

    public boolean isBurst() {
        return calculateScore() > 21;
    }

    public boolean checkScoreUnderCriterion() {
        return calculateScore() <= 21;
    }

    public void addResult(Result result) {
        this.result.put(result, this.result.getOrDefault(result, 0) + 1);
    }

    public Map<Result, Integer> getResult() {
        return Map.copyOf(result);
    }
}
