package domain.state;

import domain.card.Card;
import domain.user.Hand;

import java.util.List;

public abstract class Running implements State {

    private final Hand hand;

    public Running(Hand hand) {
        this.hand = hand;
    }

    @Override
    public State draw(final Card card) {
        hand.add(card);
        if (hand.isBlackjack()) {
            return new Blackjack(hand);
        }
        if (hand.isBust()) {
            return new Bust(hand);
        }

        return new Hit(hand);
    }

    @Override
    public State stay() {
        return new Stay(hand);
    }
    @Override
    public List<Card> cards() {
        return hand.getCards();
    }

    @Override
    public Hand hand() {
        return hand;
    }
}
