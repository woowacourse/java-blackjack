package state.finished;

import card.CardHand;
import result.GameResult;
import state.State;

public final class Bust extends Finished {
    public Bust(final CardHand cardHand) {
        super(cardHand);
    }

    @Override
    public GameResult calculatePlayerResult(State dealerState) {
        return GameResult.LOSE;
    }
}
