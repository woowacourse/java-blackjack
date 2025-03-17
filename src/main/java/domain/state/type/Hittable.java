package domain.state.type;

import domain.deck.Card;
import domain.gamer.Hand;
import domain.state.Running;
import domain.state.State;

public class Hittable extends Running {

    private static final int PLAYER_HITTABLE_THRESHOLD = 21;
    private static final int DEALER_HITTABLE_THRESHOLD = 16;

    private final int hittableThreshold;

    public Hittable(final Hand hand, final int hittableThreshold) {
        super(hand);
        this.hittableThreshold = hittableThreshold;
    }

    public static Hittable initialPlayer() {
        return new Hittable(new Hand(), PLAYER_HITTABLE_THRESHOLD);
    }

    public static Hittable initialDealer() {
        return new Hittable(new Hand(), DEALER_HITTABLE_THRESHOLD);
    }

    @Override
    public State hit(final Card card) {
        final Hand hand = getHand();
        hand.add(card);

        if (hand.isBlackjack()) {
            return new Blackjack(hand);
        }
        if (hand.isBust()) {
            return new Bust(hand);
        }
        if (hand.isBustThreshold() || hand.calculateSumOfRank() > hittableThreshold) {
            return new Stay(hand);
        }
        return new Hittable(hand, hittableThreshold);
    }

    @Override
    public State stay() {
        return new Stay(getHand());
    }
}
