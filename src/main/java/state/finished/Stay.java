package state.finished;

import card.CardHand;
import card.GameScore;
import result.GameResult;
import state.State;
import state.StateType;

public final class Stay extends Finished {
    public Stay(final CardHand cardHand) {
        super(cardHand);
    }

    @Override
    public GameResult calculatePlayerResult(State dealerState) {
        if (dealerState.type() == StateType.BUST) {
            return GameResult.WIN;
        }
        if (dealerState.type() == StateType.BLACKJACK) {
            return GameResult.LOSE;
        }
        GameScore playerScore = this.calculateScore();
        GameScore dealerScore = dealerState.calculateScore();
        return GameResult.determinePlayerResult(playerScore, dealerScore);
    }

    @Override
    public StateType type() {
        return StateType.STAY;
    }
}
