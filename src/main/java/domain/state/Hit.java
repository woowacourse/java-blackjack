package domain.state;

import domain.card.Card;
import domain.user.Hand;

import java.util.List;

public class Hit implements State {

    private final Hand hand;

    public Hit(Card card1, Card card2) {
        this(new Hand(card1, card2));
    }

    public Hit(Card card) {
        this(new Hand(card));
    }

    public Hit(Hand hand) {
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
    public List<Card> cards() {
        return hand.getCards();
    }
}
