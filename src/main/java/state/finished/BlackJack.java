package state.finished;

import card.CardHand;
import result.GameResult;

public final class BlackJack extends Finished {
    public BlackJack(final CardHand cardHand) {
        super(cardHand);
    }

    @Override
    public GameResult calculatePlayerResult(Finished dealerState) {
        if (dealerState instanceof BlackJack) {
            return GameResult.PUSH;
        }
        return GameResult.BLACKJACK;
    }
}
