package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private static final int BLACKJACK_NUMBER = 21;
    private static final int ZERO = 0;
    private static final int ACE_NUMBER_DIFFERENCE = 10;

    private final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    public int calculateTotalScore() {
        final int aceCount = getAceCardCount();
        int totalScore = checkTotalScore();

        if (isNeedDowngradeScoreAceCard(aceCount, totalScore)) {
            totalScore = checkTotalScoreWithDowngradeScoreAceCard(aceCount, totalScore);
        }

        return totalScore;
    }

    private int checkTotalScoreWithDowngradeScoreAceCard(int aceCount, int totalScore) {
        while (isNeedDowngradeScoreAceCard(aceCount, totalScore)) {
            totalScore -= ACE_NUMBER_DIFFERENCE;
            aceCount--;
        }
        return totalScore;
    }

    private boolean isNeedDowngradeScoreAceCard(final int aceCount, final int totalScore) {
        return isExistAceCard(aceCount) && isExceedBlackjackScore(totalScore);
    }

    private boolean isExceedBlackjackScore(final int totalScore) {
        return totalScore > BLACKJACK_NUMBER;
    }

    private boolean isExistAceCard(final int aceCount) {
        return aceCount > ZERO;
    }

    private int getAceCardCount() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    private int checkTotalScore() {
        return cards.stream()
                .map(Card::getValue)
                .reduce(ZERO, Integer::sum);
    }
}
