package blackjack.domain.participant;

import blackjack.domain.card.Hand;
import blackjack.domain.result.Score;
import blackjack.domain.card.Card;
import java.util.List;

public abstract class Participant {
    private final Name name;
    private final Hand hand;

    public Participant(Name name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public final String getName() {
        return name.getValue();
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
