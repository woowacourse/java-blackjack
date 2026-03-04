package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.hand.Hand;
import java.util.List;

public abstract class Participant {

    private final String name;
    private final Hand hand = new Hand();

    protected Participant(final String name) {
        this.name = name;
    }

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

    public String getName() {
        return name;
    }

    public abstract boolean canReceiveCard();
}
