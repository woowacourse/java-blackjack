package blackjack.domain.card;

import java.util.Collections;
import java.util.List;

public class Hand {
    private static final int BLACKJACK = 21;
    private static final int ACE_ALTERNATIVE_SCORE = 10;
    private static final int BLACKJACK_CARD_COUNT = 2;

    private final List<Card> cards;

    public Hand(List<Card> cards) {
        this.cards = cards;
    }

    public boolean isBust() {
        return isTotalScoreGreaterThan(BLACKJACK);
    }

    public boolean isTotalScoreGreaterThan(int score) {
        return getOptimizedScore() > score;
    }

    public boolean isBlackjack() {
        return cards.size() == BLACKJACK_CARD_COUNT && isScoreBlackjack();
    }

    private boolean isScoreBlackjack() {
        return getOptimizedScore() == BLACKJACK;
    }

    public int getOptimizedScore() {
        int cardTotalScore = getCardTotalScore();
        if (!hasAce() || cardTotalScore + ACE_ALTERNATIVE_SCORE > BLACKJACK) {
            return cardTotalScore;
        }
        return cardTotalScore + ACE_ALTERNATIVE_SCORE;
    }

    private int getCardTotalScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
