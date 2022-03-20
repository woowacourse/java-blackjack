package blakjack.domain;

import blakjack.domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public final class PrivateArea {
    private static final int UPPER_ACE_SCORE = 10;
    private static final int BLACKJACK_SCORE = 21;
    private static final int BLACKJACK_CARD_COUNT = 2;

    private final List<Card> cards = new ArrayList<>();

    public PrivateArea(final String name) {
    }

    public void addCard(final Card card) {
        cards.add(card);
    }

    public int getTotalScore() {
        final var minScore = calculateMinScore();
        final var maxScore = calculateMaxScore(minScore);

        if (maxScore > BLACKJACK_SCORE) {
            return minScore;
        }
        return maxScore;
    }

    private int calculateMinScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    private int calculateMaxScore(final int minScore) {
        if (hasAce()) {
            return minScore + UPPER_ACE_SCORE;
        }
        return minScore;
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public boolean isBlackjack() {
        return cards.size() == BLACKJACK_CARD_COUNT && getTotalScore() == BLACKJACK_SCORE;
    }

    public int getCardsSize() {
        return cards.size();
    }
}
