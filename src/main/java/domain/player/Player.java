package domain.player;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Score;

import java.util.List;

public abstract class Player {

    private final Name name;
    private final Cards cards;

    Player(final Name name, final Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    public abstract boolean canHit();

    public abstract List<Card> showCards();

    public final boolean isBust() {
        return cards.isBust();
    }

    public final void takeCard(final Card card) {
        cards.takeCard(card);
    }

    public final List<Card> getCards() {
        return cards.getCards();
    }

    public final Score getScore() {
        return cards.getScore();
    }

    public final String getName() {
        return name.getName();
    }
}
