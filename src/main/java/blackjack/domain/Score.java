package blackjack.domain;

import java.util.List;

public class Score {
    private static final int MAX_BLACKJACK_SCORE = 21;
    private static final int ACE_GAP = 10;

    private int score;

    public void calculateScore(List<TrumpNumber> trumpNumbers) {
        score = trumpNumbers.stream()
                .mapToInt(TrumpNumber::getScore)
                .sum();

        if (score > MAX_BLACKJACK_SCORE) {
            handleBust(trumpNumbers);
        }
    }

    private void handleBust(List<TrumpNumber> trumpNumbers) {
        int aceCount = countAce(trumpNumbers);
        while (score > MAX_BLACKJACK_SCORE && aceCount > 0) {
            score -= ACE_GAP;
            aceCount -= 1;
        }
    }

    private int countAce(List<TrumpNumber> trumpNumbers) {
        return (int) trumpNumbers.stream()
                .filter(number -> number.equals(TrumpNumber.ACE))
                .count();
    }

    public int getScore() {
        return score;
    }
}
