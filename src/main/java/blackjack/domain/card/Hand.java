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
        return calculateOptimizedScore() > BLACKJACK;
    }

    public boolean isBlackjack() {
        return calculateOptimizedScore() == BLACKJACK;
    }

    public int calculateOptimizedScore() {
        int cardTotalScore = getCardTotalScore();
        if (cardTotalScore >= BLACKJACK) {
            return cardTotalScore;
        }
        int aceCountForAlter = countAceForAlter(cardTotalScore);
        return aceCountForAlter * ACE_ALTERNATIVE_SCORE + cardTotalScore;
    }

    public boolean isTotalScoreGreaterThan(int score) {
        return calculateOptimizedScore() > score;
    }

    public void addCard(Card card) {
        cards.add(card);
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
        int availableAceAlterCount = (BLACKJACK - cardTotalScore) / ACE_ALTERNATIVE_SCORE;
        return Math.min(aceCount, availableAceAlterCount);
    }
}
