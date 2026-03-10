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

    public void receiveCard(final Card card) {
        hand.add(card);
    }

    public Score calculateScore() {
        return hand.calculateScore();
    }

    public boolean isBlackjack() {
        return hand.isBlackjack();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public String getName() {
        return name.getName();
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public abstract boolean canReceiveCard();
}
