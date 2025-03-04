package blackjack.domian;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class WinnerDecider {

    private final List<Card> cards;

    public WinnerDecider(List<Card> cards) {
        this.cards = cards;
    }

    public WinningResult decide(List<Card> cards) {
        int dealerScore = calculateMaxScore(this.cards);
        int playerScore = calculateMaxScore(cards);
        System.out.println("dealer: " + dealerScore + " player: " + playerScore);
        if (isBlackjack(this.cards) && !isBlackjack(cards)) {
            return WinningResult.LOSE;
        }

        if (dealerScore > 21) {
            return WinningResult.WIN;
        }

        if (dealerScore == playerScore) {
            return WinningResult.DRAW;
        }
        if (dealerScore > playerScore) {
            return WinningResult.LOSE;
        }
        return WinningResult.WIN;
    }

    public int calculateMaxScore(List<Card> cards) {
        return solve2(0, 0, cards);
    }

    private int solve2(int depth, int totalScore, List<Card> cards) {
        if (depth == cards.size()) {
            return totalScore;
        }

        Card card = cards.get(depth);
        List<Integer> scores = card.getRank().getScore();
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int score : scores) {
            int sum = solve2(depth + 1, totalScore + score, cards);
            if (sum > 21) {
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

    private boolean isBlackjack(List<Card> cards) {
        if (cards.size() != 2) {
            return false;
        }
        Set<Rank> ranks = cards.stream()
                .map(Card::getRank)
                .collect(Collectors.toSet());
        if (!ranks.contains(Rank.ACE)) {
            return false;
        }

        return ranks.contains(Rank.KING) ||
                ranks.contains(Rank.QUEEN) ||
                ranks.contains(Rank.JACK) ||
                ranks.contains(Rank.TEN);
    }
}
