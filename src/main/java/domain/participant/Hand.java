package domain.participant;

import domain.Score;
import domain.card.Card;
import java.util.ArrayList;
import java.util.List;

public class Hand {
    private final static int BUST_THRESHOLD = 21;
    private final static int ACE_ADJUST_SCORE = 10;
    private final List<Card> cards = new ArrayList<>();

    public void add(Card card) {
        this.cards.add(card);
    }

    public Score calculateScore() {
        int score = 0;
        for (Card card : cards) {
            score += card.rank().getScore();
        }

        for (Card card : cards) {
            if (card.isAce()) {
                score = calculateAcePoint(score);
            }
        }

        return new Score(score);
    }

    public Card getFirstCard() {
        return cards.getFirst();
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    private int calculateAcePoint(int currentScore) {
        if (currentScore > BUST_THRESHOLD) {
            return currentScore - ACE_ADJUST_SCORE;
        }
        return currentScore;
    }
}
