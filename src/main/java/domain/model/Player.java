package domain.model;

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

    public Score getScore() {
        return Score.of(cards);
    }

    public Cards getCards() {
        return new Cards(Set.copyOf(cards.getCards()));
    }

    public String getName() {
        return name;
    }
}
