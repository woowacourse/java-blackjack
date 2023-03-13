package domain.gamestate;

import domain.BetAmount;

public class Lose implements GameState{

    @Override
    public BetAmount calculate(BetAmount betAmount) {
        return betAmount.multiply(-1);
    }
}
