package domain;

import java.util.List;

public class ScoreCalculator {

    public static final int BLACKJACK_SCORE = 21;
    public static final int ACE_BONUS_SCORE = 10;

    public int calculateScore(List<Card> hand) {
        int currentScore = sumScore(hand);
        Card findCard = hand.stream().filter(c -> c.getRank() == 1).findAny().orElse(null);
        if (findCard == null) {
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

    private int sumScore(List<Card> hand) {
        int score = 0;
        for (Card card : hand) {
            score += card.getRank();
        }
        return score;
    }
}
