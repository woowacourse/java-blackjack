package blackjack.participant.state.playing;

import blackjack.card.Card;
import blackjack.participant.Hand;
import blackjack.participant.state.GameState;
import blackjack.participant.state.terminated.StandState;

public class DealerState extends GameState {

    private static final int MAX_DRAWING_SCORE = 16;

    public DealerState(Hand hand) {
        super(hand);
    }

    public DealerState() {
        super(new Hand());
    }

    @Override
    public GameState drawCard(Card card) {
        Hand newHand = hand.addCard(card);
        if (newHand.hasScoreGreaterThan(MAX_DRAWING_SCORE)) {
            return stand();
        }
        return new DealerState(newHand);
    }

    @Override
    public GameState stand() {
        return new StandState(hand);
    }

    @Override
    public boolean isTerminated() {
        return false;
    }
}
