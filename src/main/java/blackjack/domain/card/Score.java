package blackjack.domain.card;

import java.util.Set;

public class Score {
    private static final int DEFAULT_CARD_SIZE = 2;
    static final int BUST_THRESHOLD = 21;

    private final Cards cards;

    public Score(Cards cards) {
        this.cards = cards;
    }

    public int calculateMaxScore() {
        return maxDfs(0, 0);
    }

    private int maxDfs(int depth, int totalScore) {
        if (depth == cards.getSize()) {
            return totalScore;
        }

        Card card = cards.getCard(depth);
        Set<Integer> scores = card.getRank().getScores();
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int score : scores) {
            int sum = maxDfs(depth + 1, totalScore + score);
            if (sum > BUST_THRESHOLD) {
                min = Math.min(min, sum);
                continue;
            }
            max = Math.max(max, sum);
        }
        if (max == Integer.MIN_VALUE) {
            return min;
        }
        return max;
    }

    public int calculateMinScore() {
        return minDfs(0, 0);
    }

    private int minDfs(int depth, int totalScore) {
        if (depth == cards.getSize()) {
            return totalScore;
        }
        Card card = cards.getCard(depth);
        Set<Integer> scores = card.getRank().getScores();
        int min = Integer.MAX_VALUE;
        for (int score : scores) {
            min = Math.min(min, minDfs(depth + 1, totalScore + score));
        }
        return min;
    }

    public boolean isBlackjack() {
        return cards.getSize() == DEFAULT_CARD_SIZE
                && calculateMaxScore() == BUST_THRESHOLD;
    }

    public boolean canTake() {
        int minScore = calculateMinScore();
        return minScore < BUST_THRESHOLD;
    }

    public void additionalTake(Card card) {
        int minScore = calculateMaxScore();
        if (minScore >= BUST_THRESHOLD) {
            throw new IllegalArgumentException("카드 합이 21이 넘으므로 더 받을 수 없습니다.");
        }
        this.cards.add(card);
    }
}
