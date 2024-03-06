
package domain;

import java.util.List;

public class Dealer extends Gamer {
    private static final int HIT_CONDITION = 17;
    private final Hand hand;

    public Dealer() {
        this.hand = new Hand();
    }

    @Override
    public void hit(final Decks decks) {
        while (hand.sum() < HIT_CONDITION) {
            hand.add(decks.draw());
        }
    }
}
