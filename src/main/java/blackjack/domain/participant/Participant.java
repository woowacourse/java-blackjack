package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.hand.Hand;
import blackjack.domain.hand.Score;

import java.util.List;

public abstract class Participant {

    private final Name name;
    private final Hand hand = new Hand();

    protected Participant(final Name name) {
        this.name = name;
    }

    public final void receiveCard(final Card card) {
        hand.add(card);
    }

    public final Score calculateScore() {
        return hand.calculateScore();
    }

    public final boolean isBust() {
        return hand.isBust();
    }

    public final List<Card> getCards() {
        return hand.getCards();
    }

    public final String getFirstCard() {
        return hand.getFirstCard();
    }

    public final String getName() {
        return name.value();
    }

    public final boolean isBlackjack() {
        return hand.isBlackjack();
    }

    public abstract boolean canReceiveCard();
}
