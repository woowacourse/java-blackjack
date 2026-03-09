package domain.player;

import domain.Card;
import domain.CardDto;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Hand {
    private final static int BUST_THRESHOLD = 21;
    private final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public void add(Card card) {
        this.cards.add(card);
    }

    public int calculateScore() {
        int score = 0;
        for (Card card : cards) {
            score += card.cardNumber().getPoint();
        }

        for (Card card : cards) {
            if (card.isAce()) {
                score = calculateAcePoint(score);
            }
        }

        return score;
    }

    public CardDto snapshot() {
        return new CardDto(List.copyOf(cards));
    }

    private int calculateAcePoint(int currentScore) {
        if (currentScore > 21) {
            return currentScore - 10;
        }
        return currentScore;
    }

    public boolean isBust() {
        return calculateScore() > BUST_THRESHOLD;
    }
}
