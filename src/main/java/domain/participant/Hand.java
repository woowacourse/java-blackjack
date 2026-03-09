package domain.participant;

import domain.card.Card;
import domain.card.CardDto;
import java.util.ArrayList;
import java.util.List;

public class Hand {
    private final static int BUST_THRESHOLD = 21;
    private final static int ACE_ADJUST_SCORE = 10;
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
        if (currentScore > BUST_THRESHOLD) {
            return currentScore - ACE_ADJUST_SCORE;
        }
        return currentScore;
    }
}
