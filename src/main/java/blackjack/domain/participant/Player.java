package blackjack.domain.participant;

import blackjack.domain.card.Card;

public class Player extends Participant {

    private static final int BLACKJACK_BOUND = 21;
    private final String name;

    public Player(final String name, final Dealer dealer) {
        super();
        this.name = name;

        draw(dealer);
        draw(dealer);
    }

    public void draw(final Dealer dealer) {
        Card card = dealer.draw();
        hand.add(card);
    }

    @Override
    public boolean canReceiveCard() {
        return hand.calculateScore() < BLACKJACK_BOUND;
    }

    public String getName() {
        return name;
    }
}
