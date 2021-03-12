package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.state.*;

import java.util.List;

public abstract class Participant {
    private final String name;
    private State state;
    private final Hand hand;

    public Participant(String name, List<Card> cards) {
        this.name = name;
        this.state = StateFactory.draw(new Hand(cards));
        this.hand = new Hand(cards);
    }

    public abstract boolean isAvailableToTake();

    public void takeCard(Card card) {
        state = state.draw(card);
        hand.add(card);
    }

    public boolean isBlackjack() {
        return state instanceof Blackjack;
    }

    public boolean isBust() {
        return state instanceof Bust;
    }

    public boolean isHit() {
        return state instanceof Hit;
    }

    public String getName() {
        return name;
    }

    public State getState() {
        return state;
    }
}
