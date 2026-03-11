package blackjack.model;

import java.util.List;

public abstract class Participant {

    private final HandCards handCards;

    public Participant() {
        this.handCards = new HandCards();
    }

    public final void receiveCard(Card card) {
        handCards.addCard(card);
    }

    public final Score getScore() {
        return handCards.calculate();
    }

    public final boolean isBust() {
        return getScore().isBurst();
    }

    public boolean isBlackjack() {
        return handCards.isBlackjack();
    }

    public final Card FirstCardOpen() {
        return handCards.getFirstCard();
    }

    public final List<Card> getHandCards() {
        return handCards.getCards();
    }

    public abstract boolean canReceive();
}
