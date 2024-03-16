package blackjack.domain.gamers;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;

public class Player extends Gamer {

    private final Name name;

    private Player(final Name name, final Hand hand) {
        super(hand);
        this.name = name;
    }

    public static Player from(final String name) {
        return new Player(new Name(name), new Hand());
    }

    public void drawInitialHand(final Dealer dealer) {
        for (int i = 0; i < INITIAL_CARD_COUNT; i++) {
            hand.add(dealer.drawPlayerCard());
        }
    }

    public void draw(final Card card) {
        hand.add(card);
    }

    @Override
    public boolean canDraw() {
        return hand.calculateOptimalSum() <= BLACKJACK;
    }

    public Name getName() {
        return name;
    }
}
