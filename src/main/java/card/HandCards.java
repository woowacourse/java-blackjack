package card;

import java.util.ArrayList;
import java.util.List;

public class HandCards {
    private static final int BLACKJACK_SCORE = 21;

    private final List<Card> cards;

    public HandCards() {
        this.cards = new ArrayList<>();
    }

    public void addAll(List<Card> cards) {
        this.cards.addAll(cards);
    }

    public int computeOptimalSum() {
        List<Integer> candidates = new ArrayList<>();
        computeCandidates(0, 0, cards, candidates);

        return candidates.stream()
                .filter(sum -> sum <= BLACKJACK_SCORE)
                .max(Integer::compareTo)
                .orElseGet(() -> candidates.stream()
                        .filter(sum -> sum > BLACKJACK_SCORE)
                        .min(Integer::compareTo)
                        .orElseThrow(() -> new IllegalStateException("논리적으로 도달할 수 없는 예외입니다.")));
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

    public boolean isBust() {
        return computeOptimalSum() > BLACKJACK_SCORE;
    }

    public boolean isBlackjack() {
        return size() == 2 && computeOptimalSum() == BLACKJACK_SCORE;
    }

    public int size() {
        return cards.size();
    }

    public List<Card> getCards(int count) {
        return cards.subList(0, count);
    }

    public List<Card> getCards() {
        return cards;
    }
}
