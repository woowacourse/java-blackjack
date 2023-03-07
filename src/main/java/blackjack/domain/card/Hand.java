package blackjack.domain.card;

import java.util.List;
import java.util.Objects;

public class Hand {

    private static final int BLACKJACK_NUMBER = 21;
    private static final int ZERO = 0;
    private static final int ACE_NUMBER_DIFFERENCE = 10;

    private final List<Card> cards;

    public Hand(final List<Card> cards) {
        this.cards = cards;
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getScore() {
        final int aceCount = getAceCardCount();
        final int score = originalScore();

        return calculateScore(aceCount, score);
    }

    private int calculateScore(int aceCount, int score) {
        if (isNeedDowngradeScoreAceCard(aceCount, score)) {
            return scoreWithDowngradeScoreAceCard(aceCount, score);
        }
        return score;
    }

    private int originalScore() {
        return cards.stream()
                .map(Card::getValue)
                .reduce(ZERO, Integer::sum);
    }

    private int scoreWithDowngradeScoreAceCard(int aceCount, int totalScore) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hand hand = (Hand) o;
        return Objects.equals(cards, hand.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }
}
