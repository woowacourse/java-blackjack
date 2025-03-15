package blackjack.model.state.running;

import blackjack.model.card.Card;
import blackjack.model.state.Hand;
import blackjack.model.state.State;
import blackjack.model.state.finished.Blackjack;
import blackjack.model.state.finished.Bust;
import blackjack.model.state.finished.Stand;

public final class DealerDrawing extends RunningState {

    private static final int DEALER_HIT_THRESHOLD = 16;

    public DealerDrawing() {
        this(new Hand());
    }

    private DealerDrawing(Hand hand) {
        super(hand);
    }

    @Override
    public State receiveCard(Card card) {
        Hand newHand = hand.add(card);
        if (newHand.size() == ONE) {
            return new DealerDrawing(newHand);
        }
        if (newHand.size() == BLACKJACK_CARD_COUNT && newHand.getTotal() == MAX_SCORE) {
            return new Blackjack(newHand);
        }
        if (MAX_SCORE < newHand.getTotal()) {
            return new Bust(newHand);
        }
        if (newHand.getTotal() <= DEALER_HIT_THRESHOLD) {
            return new DealerDrawing(newHand);
        }
        return new Stand(newHand);
    }

    @Override
    public State stand() {
        throw new IllegalArgumentException("딜러는 처음부터 스탠드할 수 없습니다.");
    }
}
