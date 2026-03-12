package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.hand.Hand;
import blackjack.domain.hand.Score;

import java.util.Collections;
import java.util.List;

public abstract class Participant {
    private final Hand hand;

    public Participant() {
        this.hand = new Hand();
    }

    public void receiveCard(Card card) {
        hand.add(card);
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public Score getScore() {
        return hand.calculateScore();
    }

    public String getInitialCards() {
        return hand.getFirst().getCardName();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(hand.getCards());
    }

    public boolean isBlackjack() {
        return hand.isBlackjack();
    }

    public abstract boolean canReceive();
}
