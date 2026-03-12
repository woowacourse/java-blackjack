package blackjack.model;

import java.util.List;

public abstract class Participant {
    private final Name name;
    private final Hand hand;

    public Participant(Name name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public final String getName() {
        return name.getCleaned();
    }

    public final List<Card> getCards() {
        return hand.getCards();
    }

    public final void hit(Card card) {
        hand.addCard(card);
    }

    public final Score getScore() {
        return hand.getScore();
    }

    public final boolean isBust() {
        return hand.getScore().isBust();
    }

    public abstract boolean canHit();

    public abstract List<Card> getInitialVisibleCards();
}
