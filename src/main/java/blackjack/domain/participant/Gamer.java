package blackjack.domain.participant;

import blackjack.domain.Hand;
import blackjack.domain.card.TrumpCard;

import java.util.List;

public abstract class Gamer {

    protected final Hand hand;

    public Gamer() {
        this.hand = new Hand();
    }

    abstract boolean canReceiveCard();

    public boolean isBust() {
        return hand.isBust();
    }

    public boolean isBlackjack() {
        return hand.isBlackjack();
    }

    public long getScore() {
        return hand.calculateScore();
    }

    public List<TrumpCard> getHandCards() {
        return hand.getCards();
    }
}
