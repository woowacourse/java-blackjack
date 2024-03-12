package blackjack.player.state.playing;

import blackjack.card.Card;
import blackjack.player.Hand;
import blackjack.player.state.GameState;
import blackjack.player.state.terminated.StandState;

public class DealerState extends PlayingState {

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
            return new StandState(newHand);
        }
        return new DealerState(newHand);
    }
}
