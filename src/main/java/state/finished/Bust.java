package state.finished;

import card.CardHand;
import result.GameResult;

public final class Bust extends Finished {
    public Bust(final CardHand cardHand) {
        super(cardHand);
    }

    @Override
    public GameResult calculatePlayerResult(Finished dealerState) {
        return GameResult.LOSE;
    }
}
