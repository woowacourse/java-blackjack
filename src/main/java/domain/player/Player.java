package domain.player;

import domain.card.Card;
import domain.card.Cards;

import java.util.List;

public abstract class Player {

    private static final int BUST_NUMBER = 22;

    private final Name name;
    private final Cards cards;

    Player(final Name name, final Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    public final boolean isBust() {
        return getScore() >= BUST_NUMBER;
    }

    public final void takeCard(final Card card) {
        cards.takeCard(card);
    }

    public final List<Card> getCards() {
        return cards.getCards();
    }

    public final int getScore() {
        return cards.getScore();
    }

    public final String getName() {
        return name.getName();
    }
}
