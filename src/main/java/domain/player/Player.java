package domain.player;

import domain.card.Card;
import domain.card.Hand;
import domain.card.Score;

import java.util.List;

public abstract class Player {

    private final Name name;
    private final Hand hand;

    Player(final Name name, final Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public abstract boolean canHit();

    public abstract List<Card> showCards();

    public final boolean isBust() {
        return hand.isBust();
    }

    public final void takeCard(final Card card) {
        hand.takeCard(card);
    }

    public final List<Card> getCards() {
        return hand.getCards();
    }

    public final Score score() {
        return hand.getScore();
    }

    public final int getScore() {
        return score().getScore();
    }

    public final String getName() {
        return name.getName();
    }
}
