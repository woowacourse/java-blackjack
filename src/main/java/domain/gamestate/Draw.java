package domain.gamestate;

import domain.BetAmount;

public class Draw implements GameState{

    @Override
    public BetAmount calculate(BetAmount betAmount) {
        return BetAmount.getZero();
    }
}
