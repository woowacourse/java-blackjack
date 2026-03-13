package blackjack.domain.participant;

import blackjack.domain.card.Hand;
import blackjack.domain.result.Score;
import blackjack.domain.card.Card;
import java.util.List;

public abstract class Participant {
    private final Name NAme;
    private final Hand hand;

    public Participant(Name NAme, Hand hand) {
        this.NAme = NAme;
        this.hand = hand;
    }

    public final String getName() {
        return NAme.getCleaned();
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
        return hand.isBust();
    }

    public abstract boolean canHit();
}
