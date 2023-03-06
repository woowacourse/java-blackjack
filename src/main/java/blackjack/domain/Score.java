package blackjack.domain;

import java.util.List;

public class Score {
    private static final int MAX_BLACKJACK_SCORE = 21;
    private static final int ACE_GAP = 10;

    private int score;

    public void calculateScore(List<CardNumber> cardNumbers) {
        score = cardNumbers.stream()
                .mapToInt(CardNumber::getScore)
                .sum();

        if (score > MAX_BLACKJACK_SCORE) {
            handleBust(cardNumbers);
        }
    }

    private void handleBust(List<CardNumber> cardNumbers) {
        int aceCount = countAce(cardNumbers);
        while (score > MAX_BLACKJACK_SCORE && aceCount > 0) {
            score -= ACE_GAP;
            aceCount -= 1;
        }
    }

    private int countAce(List<CardNumber> cardNumbers) {
        return (int) cardNumbers.stream()
                .filter(number -> number.equals(CardNumber.ACE))
                .count();
    }

    public int getScore() {
        return score;
    }
}
