package blackjack.domain.card;

import java.util.Collections;
import java.util.List;

public class Hand {
    private static final int BLACKJACK = 21;
    private static final int ACE_ALTERNATIVE_SCORE = 10;

    private final List<Card> cards;

    public Hand(List<Card> cards) {
        this.cards = cards;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public boolean isBust() {
        return isTotalScoreGreaterThan(BLACKJACK);
    }

    public boolean isBlackjack() {
        return getOptimizedScore() == BLACKJACK;
    }

    public int getOptimizedScore() {
        int cardTotalScore = getCardTotalScore();
        if (cardTotalScore >= BLACKJACK) {
            return cardTotalScore;
        }
        int aceCountForAlter = countAceForAlter();
        return aceCountForAlter * ACE_ALTERNATIVE_SCORE + cardTotalScore;
    }

    public boolean isTotalScoreGreaterThan(int score) {
        return getCardTotalScore() > score;
    }

    private int getCardTotalScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    private int countAceForAlter() {
        int aceCount = (int)cards.stream()
                .filter(Card::isAce)
                .count();
        int cardTotalScore = getCardTotalScore();
        return Math.min(aceCount, (BLACKJACK - cardTotalScore) / ACE_ALTERNATIVE_SCORE);
    }
}
