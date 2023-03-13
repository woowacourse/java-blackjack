package domain.gamestate;

import domain.BetAmount;

public interface GameState {

    BetAmount calculate(BetAmount betAmount);
}
