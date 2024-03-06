package blackjack.domain.participant;

import blackjack.domain.Hand;
import blackjack.domain.card.Card;

public class Player implements Participant {

    private static final int BLACKJACK_BOUND = 21;

    private final String name;
    private final Hand hand;

    public Player(final String name) {
        this.name = name;
        this.hand = new Hand();
    }

    public void addCard(final Card... cards) {
        hand.add(cards);
    }

    @Override
    public boolean canReceiveCard() {
        return hand.calculateScore() < BLACKJACK_BOUND;
    }
}
