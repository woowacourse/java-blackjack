package blackjack.domain.participant;

import blackjack.domain.Deck;

public class Player extends Participant {

    private static final int BLACKJACK_BOUND = 21;

    public Player(final String name, final Deck deck) {
        super(name);

        draw(deck);
        draw(deck);
    }

    @Override
    public boolean canReceiveCard() {
        return hand.calculateScore() < BLACKJACK_BOUND;
    }
}
