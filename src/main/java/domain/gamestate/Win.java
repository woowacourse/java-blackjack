package domain.gamestate;

import domain.BetAmount;

public class Win implements GameState {

    @Override
    public BetAmount calculate(BetAmount betAmount) {
        return betAmount;
    }
}
