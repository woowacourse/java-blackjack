package blackjack.domian;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Player {
    private final List<Card> cards;

    public Player(List<Card> cards) {
        this.cards = cards;
    }

    public void send(Card... cards) {
        int minScore = calculateMinScore();
        if (minScore >= 21) {
            throw new IllegalArgumentException("카드 합이 21이 넘으므로 더 받을 수 없습니다.");
        }
        this.cards.addAll(Arrays.asList(cards));
    }

    private int calculateMinScore() {
        return solve(0, 0);
    }

    private int solve(int depth, int totalScore) {
        if (depth == cards.size()) {
            return totalScore;
        }

        Card card = cards.get(depth);
        List<Integer> scores = card.getRank().getScore();
        int min = Integer.MAX_VALUE;
        for (int score : scores) {
            min = Math.min(min, solve(depth + 1, totalScore + score));
        }
        return min;
    }

    public int calculateMaxScore() {
        return solve2(0, 0);
    }

    private int solve2(int depth, int totalScore) {
        if (depth == cards.size()) {
            return totalScore;
        }

        Card card = cards.get(depth);
        List<Integer> scores = card.getRank().getScore();
        int max = Integer.MIN_VALUE;
        for (int score : scores) {
            int sum = solve2(depth + 1, totalScore + score);
            if (sum > 21) {
                continue;
            }
            max = Math.max(max, sum);
        }
        return max;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
