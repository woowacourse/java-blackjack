package domain.participant;

import domain.card.Card;
import domain.card.Hand;

import static domain.BlackjackGame.BLACKJACK_SCORE;

public abstract class Participant {

    private final Name name;
    private final Hand hand;

    public Participant(final Name name) {
        this.name = name;
        hand = new Hand();
    }

    public abstract boolean canReceiveMoreCard();

    public void receive(final Card card) {
        hand.add(card);
    }

    public Card peekCard() {
        return hand.peek();
    }

    public int score() {
        return hand.score();
    }

    public boolean isBust() {
        return hand.getCards().sum() > BLACKJACK_SCORE;
    }

    public boolean isBlackjack() {
        return hand.getCards().sum() == BLACKJACK_SCORE;
    }

    public Name name() {
        return new Name(name);
    }

    public Hand hand() {
        return new Hand(hand);
    }
}
