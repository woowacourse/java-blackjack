package blackjack.domain.card;

import blackjack.domain.game.Score;

import java.util.List;
import java.util.Objects;

public final class Hand {

    private static final int ZERO = 0;
    private static final int ACE_NUMBER_DIFFERENCE = 10;

    private final List<Card> cards;

    private Hand(final List<Card> cards) {
        this.cards = cards;
    }

    public static Hand from(final List<Card> cards) {
        return new Hand(cards);
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public Score calculateScore() {
        final int aceCount = aceCardCount();
        final Score score = calculateOriginalScore();

        return calculateDowngradeAceCardScore(aceCount, score);
    }

    private Score calculateDowngradeAceCardScore(final int aceCount, final Score score) {
        if (isNeedDowngradeScoreAceCard(aceCount, score)) {
            return calculateScoreRegardAce(aceCount, score);
        }
        return score;
    }

    private Score calculateOriginalScore() {
        final int score = cards.stream()
                .map(Card::getValue)
                .reduce(ZERO, Integer::sum);

        return Score.from(score);
    }

    private Score calculateScoreRegardAce(int aceCount, Score totalScore) {
        while (isNeedDowngradeScoreAceCard(aceCount, totalScore)) {
            totalScore = totalScore.minus(ACE_NUMBER_DIFFERENCE);
            aceCount--;
        }
        return totalScore;
    }

    private boolean isNeedDowngradeScoreAceCard(final int aceCount, final Score totalScore) {
        return isExistAceCard(aceCount) && totalScore.isBust();
    }

    private boolean isExistAceCard(final int aceCount) {
        return aceCount > ZERO;
    }

    private int aceCardCount() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    public int size() {
        return cards.size();
    }

    public List<Card> getCards() {
        return cards;
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
