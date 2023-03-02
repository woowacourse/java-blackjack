package domain.model;

import java.util.Set;

public class Dealer {

    private static final int MIN_SCORE_THRESHOLD = 16;
    private final Cards cards;
    private Score score;

    public Dealer(final Cards cards) {
        this.cards = cards;
        this.score = makeScore(cards);
    }

    private Score makeScore(final Cards cards) {
        return Score.of(cards);
    }

    public void addCard(final Card card) {
        cards.add(card);
        score = makeScore(cards);
    }

    public boolean isBust() {
        return score.isBust();
    }

    public boolean canReceiveCard() {
        return score.getValue() <= MIN_SCORE_THRESHOLD;
    }

    public Cards getCards() {
        return new Cards(Set.copyOf(cards.getCards()));
    }

    public Score getScore() {
        return score;
    }
}
