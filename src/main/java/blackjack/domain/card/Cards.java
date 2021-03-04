package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static blackjack.domain.card.Score.BLACK_JACK;

public class Cards {

    public static final int ZERO_COUNT = 0;
    public static final int ADDITIONAL_ACE_SCORE = 10;

    private final List<Card> cards;

    public Cards() {
        cards = new ArrayList<>();
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public Score getScore() {
        int score = 0;
        for (Card card : cards) {
            score += card.getDenomination().getScore();
        }
        if (countAce() != ZERO_COUNT && score + ADDITIONAL_ACE_SCORE <= BLACK_JACK) {
            score += ADDITIONAL_ACE_SCORE;
        }
        return new Score(score);
    }

    public int countAce() {
        return (int) cards.stream()
                .filter(card -> card.isAce())
                .count();
    }

    public boolean isBust() {
        return getScore().isBust();
    }

    public List<Card> cards() {
        return Collections.unmodifiableList(cards);
    }
}
