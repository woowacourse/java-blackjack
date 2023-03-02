package domain.model;

import java.util.Set;

public abstract class Participant {

    private final Cards cards;
    private Score score;

    public Participant(final Cards cards) {
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

    public Cards getCards() {
        return new Cards(Set.copyOf(cards.getCards()));
    }

    public Score getScore() {
        return score;
    }
}
