package domain.model;

import domain.vo.Score;
import java.util.Set;

public abstract class Participant {

    private final Cards cards;

    public Participant(final Cards cards) {
        this.cards = cards;
    }

    public void addCard(final Card card) {
        cards.add(card);
    }

    public boolean isBust() {
        return Score.of(cards).isBust();
    }

    public boolean cardsIsNotEmpty() {
        return cards.isNotEmpty();
    }

    public Score getScore() {
        return Score.of(cards);
    }

    public Set<Card> getCards() {
        return Set.copyOf(cards.getCards());
    }
}
