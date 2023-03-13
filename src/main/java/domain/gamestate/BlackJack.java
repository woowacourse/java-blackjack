package domain.gamestate;

import domain.BetAmount;

public class BlackJack extends Win{

    @Override
    public BetAmount calculate(BetAmount betAmount) {
        return betAmount.multiply(1.5);
    }
}
