package domain.model;

import domain.vo.Score;
import java.util.Set;

public class Player {

    private final Cards cards;
    private final String name;

    public Player(final Cards cards, final String name) {
        this.cards = cards;
        this.name = name;
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

    public String getName() {
        return name;
    }
}
