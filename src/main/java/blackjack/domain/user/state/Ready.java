package blackjack.domain.user.state;

import blackjack.domain.card.Card;
import blackjack.domain.user.Hand;

public class Ready extends Running {

    private static final int HIT_SIZE = 2;

    private Ready(Hand hand) {
        super(hand);
    }

    public Ready() {
        this(new Hand());
    }

    @Override
    public State hit(Card card) {
        Hand added = hand.addCard(card);
        if (added.isBlackJack()) {
            return new BlackJack(added);
        }
        if (added.size() == HIT_SIZE) {
            return new Hit(added);
        }
        return new Ready(added);
    }

    @Override
    public State stay() {
        throw new IllegalStateException("ready - stay ?");
    }

}
