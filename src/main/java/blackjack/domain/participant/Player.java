package blackjack.domain.participant;

import static blackjack.domain.card.Hand.BLACKJACK_SYMBOL_SCORE;

import blackjack.domain.card.Hand;

public class Player extends Participant {

    public Player(String name) {
        this(new Name(name));
    }

    public Player(Name name) {
        this(name, new Hand());
    }

    public Player(Name name, Hand cardHand) {
        super(name, cardHand);
    }

    @Override
    public boolean shouldReceive() {
        return !cardHand.isBust() && !cardHand.isBlackjack()
            && cardHand.getScore() != BLACKJACK_SYMBOL_SCORE;
    }
}
