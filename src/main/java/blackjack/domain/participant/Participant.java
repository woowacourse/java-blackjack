package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.hand.Hand;
import java.util.List;

public abstract class Participant {

    private final Hand hand = new Hand();

    public void receiveCard(final Card card) {
        hand.add(card);
    }

    public int calculateScore() {
        return hand.calculateScore();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public abstract String getName();

    public abstract boolean canReceiveCard();
}
