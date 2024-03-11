package blackjack.domain.card;

import java.util.Collections;
import java.util.List;

public class Hand {
    private static final int BLACKJACK = 21;
    private static final int ACE_ALTERNATIVE_SCORE = 10;
    private static final int NATURAL_BLACKJACK_CARD_COUNT = 2;

    private final List<Card> cards;

    public Hand(List<Card> cards) {
        this.cards = cards;
    }

    public boolean isBust() {
        return isTotalScoreGreaterThan(BLACKJACK);
    }

    public boolean isTotalScoreGreaterThan(int score) {
        return getCardTotalScore() > score;
    }

    public boolean isNaturalBlackjack() {
        return isScoreBlackjack() && cards.size() == NATURAL_BLACKJACK_CARD_COUNT;
    }

    public boolean isScoreBlackjack() {
        return getOptimizedScore() == BLACKJACK;
    }

    public int getOptimizedScore() {
        int cardTotalScore = getCardTotalScore();
        if (cardTotalScore >= BLACKJACK) {
            return cardTotalScore;
        }
        int aceCountForAlter = countAceForAlter(cardTotalScore);
        return aceCountForAlter * ACE_ALTERNATIVE_SCORE + cardTotalScore;
    }

    private int getCardTotalScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    private int countAceForAlter(int cardTotalScore) {
        int aceCount = (int) cards.stream()
                .filter(Card::isAce)
                .count();
        int remainedScoreToBlackjack = BLACKJACK - cardTotalScore;
        return Math.min(aceCount, remainedScoreToBlackjack / ACE_ALTERNATIVE_SCORE);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
