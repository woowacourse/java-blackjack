package domain;

import domain.constant.Rank;
import java.util.List;

public class ScoreCalculator {

    public static final int BLACKJACK_SCORE = 21;
    public static final int ACE_BONUS_SCORE = 10;

    private int sumScore(List<Card> hand) {
        int score = 0;
        for (Card card : hand) {
            score += card.getRankValue();
        }
        return score;
    }

    public int calculateScore(List<Card> hand) {
        int currentScore = sumScore(hand);
        boolean hasACE = hand.stream()
                .anyMatch(c -> c.getRank() == Rank.ACE);

        if (!hasACE) {
            return currentScore;
        }

        if (currentScore + ACE_BONUS_SCORE > BLACKJACK_SCORE) {
            return currentScore;
        }

        return currentScore + ACE_BONUS_SCORE;
    }

    public boolean isBust(int score) {
        return score > 21;
    }
}
