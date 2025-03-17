package state.finished;

import card.CardHand;
import card.GameScore;
import result.GameResult;

public final class Stay extends Finished {
    public Stay(final CardHand cardHand) {
        super(cardHand);
    }

    @Override
    public GameResult calculatePlayerResult(Finished dealerState) {
        if (dealerState instanceof Bust) {
            return GameResult.WIN;
        }
        if (dealerState instanceof BlackJack) {
            return GameResult.LOSE;
        }
        GameScore playerScore = this.calculateScore();
        GameScore dealerScore = dealerState.calculateScore();
        return GameResult.determinePlayerResult(playerScore, dealerScore);
    }
}
