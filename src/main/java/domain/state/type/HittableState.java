package domain.state.type;

import domain.deck.Card;
import domain.gamer.Hand;
import domain.state.RunningState;
import domain.state.State;

public class HittableState extends RunningState {

    private static final int PLAYER_HITTABLE_THRESHOLD = 21;
    private static final int DEALER_HITTABLE_THRESHOLD = 16;

    private final int hittableThreshold;

    public HittableState(final Hand hand, final int hittableThreshold) {
        super(hand);
        this.hittableThreshold = hittableThreshold;
    }

    public static HittableState initializePlayerState() {
        return new HittableState(new Hand(), PLAYER_HITTABLE_THRESHOLD);
    }

    public static HittableState initializeDealerState() {
        return new HittableState(new Hand(), DEALER_HITTABLE_THRESHOLD);
    }

    @Override
    public StateType type() {
        return StateType.HITTABLE_STATE;
    }

    @Override
    public State hit(final Card card) {
        final Hand hand = getHand();
        hand.add(card);

        if (hand.isBlackjack()) {
            return new BlackjackState(hand);
        }
        if (hand.isBust()) {
            return new BustState(hand);
        }
        if (hand.isBustThreshold() || hand.calculateSumOfRank() > hittableThreshold) {
            return new StayState(hand);
        }
        return new HittableState(hand, hittableThreshold);
    }

    @Override
    public State stay() {
        return new StayState(getHand());
    }
}
