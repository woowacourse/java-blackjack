package blackjack.domain.cards;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private static final int BLACKJACK = 21;
    private static final int BLACKJACK_CARD_COUNT = 2;

    private final List<Card> cards;

    public Hand(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public Hand() {
        this(new ArrayList<>());
    }

    public List<Card> unwrap() {
        return new ArrayList<>(cards);
    }

    public int getScore() {
        int score = totalScore();
        long multipleValueCardCount = countMultipleValue();

        for (int i = 0; i < multipleValueCardCount; i++) {
            score = changeAnotherValueIfNotBust(score);
        }
        return score;
    }

    private long countMultipleValue() {
        return cards.stream()
            .filter(Card::hasMultipleValue)
            .count();
    }

    private int totalScore() {
        return cards.stream()
            .mapToInt(Card::getScore)
            .sum();
    }

    private int changeAnotherValueIfNotBust(int score) {
        int newScore = score + CardValue.MULTIPLE_VALUE_DIFFERENCE;
        if (newScore <= BLACKJACK) {
            return newScore;
        }
        return score;
    }

    public boolean isBust() {
        return getScore() > BLACKJACK;
    }

    public boolean isBlackJack() {
        return (cards.size() == BLACKJACK_CARD_COUNT) && (getScore() == BLACKJACK);
    }

    public void addCard(Card card) {
        cards.add(card);
    }
}
