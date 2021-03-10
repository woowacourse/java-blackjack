package blackjack.domain.cards;

import java.util.ArrayList;
import java.util.List;

public class Hand {

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
        int score = hand.stream().mapToInt(Card::getScore).sum();
        long multipleValueCardCount = hand.stream().filter(Card::hasMultipleValue).count();

        for (int i = 0; i < multipleValueCardCount; i++) {
            score = changeAnotherValueIfNotBust(score);
        }
        return score;
    }

    private int changeAnotherValueIfNotBust(int score) {
        int newScore = score + CardValue.MULTIPLE_VALUE_DIFFERENCE;
        if (newScore <= BLACKJACK) {
            return newScore;
        }
        return score;
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
