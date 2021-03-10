package blackjack.domain.cards;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private static final int DEFAULT_SCORE = 0;
    private static final int BLACKJACK = 21;
    private static final int BLACKJACK_CARD_COUNT = 2;

    private final List<Card> hand;

    public Hand(List<Card> cards) {
        hand = new ArrayList<>(cards);
    }

    public Hand() {
        this(new ArrayList<>());
    }

    public int getScore() {
        return calculateScore(new ArrayList<>(hand), DEFAULT_SCORE);
    }

    private int calculateScore(List<Card> leftCards, int currentScore) {
        if (leftCards.isEmpty()) {
            return currentScore;
        }

        Card cardHead = leftCards.get(0);
        List<Card> cardsTail = leftCards.subList(1, leftCards.size());

        return findOptimalScore(cardHead, cardsTail, currentScore);
    }

    private int findOptimalScore(Card cardHead, List<Card> cardsTail, int currentScore) {
        int totalScore = currentScore + cardHead.getScore();
        if (cardHead.hasMultipleValue() && calculateScore(cardsTail, totalScore) > BLACKJACK) {
            return calculateScore(cardsTail, currentScore + CardValue.getMultipleValue());
        }
        return calculateScore(cardsTail, totalScore);
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public boolean isBust() {
        return getScore() > BLACKJACK;
    }

    public boolean isBlackJack() {
        return (hand.size() == BLACKJACK_CARD_COUNT) && (getScore() == BLACKJACK);
    }

    public List<Card> unwrap() {
        return new ArrayList<>(hand);
    }
}
