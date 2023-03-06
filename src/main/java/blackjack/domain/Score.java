package blackjack.domain;

import java.util.List;

public class Score {
    private static final int MAX_BLACKJACK_SCORE = 21;
    private static final int ACE_GAP = 10;

    private int score;

    public void calculateScore(List<Letter> letters) {
        score = letters.stream()
                .mapToInt(Letter::getScore)
                .sum();

        if (score > MAX_BLACKJACK_SCORE) {
            handleBurst(letters);
        }
    }

    private void handleBurst(List<Letter> letters) {
        int aceCount = countAce(letters);
        while (score > MAX_BLACKJACK_SCORE && aceCount > 0) {
            score -= ACE_GAP;
            aceCount -= 1;
        }
    }

    private int countAce(List<Letter> letters) {
        return (int) letters.stream()
                .filter(e -> e.equals(Letter.ACE))
                .count();
    }

    public int getScore() {
        return score;
    }
}
