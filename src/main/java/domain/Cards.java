package domain;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    private static final int BUST_LIMIT = 21;

    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public void addAll(List<Card> cards) {
        this.cards.addAll(cards);
    }

    public int size() {
        return cards.size();
    }

    public int computeOptimalSum() {
        List<Integer> candidates = new ArrayList<>();
        computeCandidates(0, 0, cards, candidates);

        return candidates.stream()
                .filter(sum -> sum <= BUST_LIMIT)
                .max(Integer::compareTo)
                .orElseGet(() -> candidates.stream()
                        .filter(sum -> sum > BUST_LIMIT)
                        .min(Integer::compareTo)
                        .orElseThrow(() -> new IllegalStateException("논리적으로 도달할 수 없는 예외입니다.")));
    }

    public boolean isBust() {
        return computeOptimalSum() > BUST_LIMIT;
    }

    private void computeCandidates(int cardIndex, int sum, List<Card> cards, List<Integer> candidates) {
        if (cardIndex == cards.size()) {
            candidates.add(sum);
            return;
        }

        for (int score : cards.get(cardIndex).getScores()) {
            computeCandidates(cardIndex + 1, sum + score, cards, candidates);
        }
    }

    public List<Card> getCards() {
        return cards;
    }

    public List<Card> getCards(int count) {
        return cards.subList(0, count);
    }
}
