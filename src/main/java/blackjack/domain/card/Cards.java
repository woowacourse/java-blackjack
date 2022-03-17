package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Cards {

    private static final int MAX_SCORE = 21;

    private final Collection<Card> cards;
    private boolean blackjack = false;
    private boolean maxScore = false;

    public Cards(Card first, Card second) {
        cards = new ArrayList<>();
        cards.add(first);
        cards.add(second);
        if (calculateScore() == MAX_SCORE) {
            blackjack = true;
        }
    }

    public void add(Card card) {
        cards.add(card);
        if (calculateScore() == MAX_SCORE) {
            maxScore = true;
        }
    }

    public int calculateScore() {
        int score = cards.stream()
                .mapToInt(Card::getValue)
                .sum();

        int aceCount = (int) cards.stream()
                .filter(Card::isAce)
                .count();

        score = handleAce(score, aceCount);

        if (score > MAX_SCORE) {
            score = 0;
        }

        return score;
    }

    public boolean isBlackjack() {
        return blackjack;
    }

    public boolean isMaxScore() {
        return maxScore;
    }

    public boolean isBust() {
        return calculateScore() == 0;
    }

    private int handleAce(int score, int aceCount) {
        while (isAbleToAddAdditionalAceValue(score, aceCount)) {
            score += Card.ADDITIONAL_ACE_VALUE;
            aceCount--;
        }
        return score;
    }

    private boolean isAbleToAddAdditionalAceValue(int score, int aceCount) {
        int expectedScore = score + Card.ADDITIONAL_ACE_VALUE;
        return aceCount > 0 && expectedScore <= MAX_SCORE;
    }

    public List<Card> toList() {
        return new ArrayList<>(cards);
    }
}
