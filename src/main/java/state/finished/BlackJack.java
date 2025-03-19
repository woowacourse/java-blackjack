package state.finished;

import card.CardHand;
import result.GameResult;
import state.State;
import state.StateType;

public final class BlackJack extends Finished {
    public BlackJack(final CardHand cardHand) {
        super(cardHand);
    }

    @Override
    public GameResult calculatePlayerResult(State dealerState) {
        if (dealerState.type() == StateType.BLACKJACK) {
            return GameResult.PUSH;
        }
        return GameResult.BLACKJACK;
    }

    @Override
    public StateType type() {
        return StateType.BLACKJACK;
    }
}
