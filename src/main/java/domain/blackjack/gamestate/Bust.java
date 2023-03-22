package domain.blackjack.gamestate;

import domain.blackjack.Result;
import domain.card.Card;
import domain.card.Cards;

public class Bust extends GameState {
    public Bust(Cards cards, HandState handState) {
        super(cards, handState);
    }

    @Override
    public GameState receive(Card card) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isAbleToReceiveCard() {
        return false;
    }

    @Override
    public Result competeToOtherState(GameState otherState) {
        if (handState.isEqualState(otherState.handState)) {
            return Result.DRAW;
        }

        return Result.LOSE;
    }

    @Override
    public double getEarningRate() {
        return DEFAULT_EARN_RATE;
    }
}
