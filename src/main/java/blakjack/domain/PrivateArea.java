package blakjack.domain;

import blakjack.domain.card.Card;

import java.util.ArrayList;
import java.util.List;

import static blakjack.domain.participant.Dealer.DEALER_NAME;

public final class PrivateArea {
    private static final int UPPER_ACE_SCORE = 10;
    private static final int BLACKJACK_SCORE = 21;
    private static final int BLACKJACK_CARD_COUNT = 2;

    private final List<Card> cards = new ArrayList<>();
    private final String name;

    public PrivateArea(final String name) {
        this.name = name;
    }

    public void addCard(final Card card) {
        cards.add(card);
    }

    public int getTotalScore() {
        final int minScore = calculateMinScore();
        final int maxScore = calculateMaxScore(minScore);

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

    public boolean isBust() {
        return getTotalScore() > BLACKJACK_SCORE;
    }

    public boolean isDealer() {
        return DEALER_NAME.equals(name);
    }

    public int getCardsSize() {
        return cards.size();
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }
}
