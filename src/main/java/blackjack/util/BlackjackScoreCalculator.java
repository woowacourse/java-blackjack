package blackjack.util;

import blackjack.domain.Status;
import blackjack.domain.card.Deck;

public class BlackjackScoreCalculator implements ScoreCalculator {

    private static final int ACE_EXCEPTION_NUMBER = 10;

    @Override
    public int apply(Deck deck) {
        return getScore(deck);
    }

    private int getScore(Deck deck) {
        int totalScore = deck.totalScore();
        for (long i = 0; i < deck.countOfAce(); i++) {
            totalScore = checkAce(totalScore);
        }
        return totalScore;
    }

    private int checkAce(int totalScore) {
        int withAceScore = totalScore + ACE_EXCEPTION_NUMBER;
        if (Status.evaluateScore(withAceScore) != Status.BURST) {
            totalScore = withAceScore;
        }
        return totalScore;
    }

}
