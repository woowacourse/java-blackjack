package domain;

import util.BlackJackRule;

public class Player extends User {
    public Player(Name name) {
        super(name);
    }

    @Override
    public boolean canHit() {
        if (cards.isBust()) {
            return false;
        }
        if (cards.isBlackJack()) {
            return false;
        }
        return true;
    }

    public WinningResult getWinningResult(int dealerScore) {
        if (cards.isBust()) {
            return WinningResult.LOSE;
        }
        if (BlackJackRule.isBust(dealerScore)) {
            return WinningResult.WIN;
        }
        if (dealerScore < getScore()) {
            return WinningResult.WIN;
        }
        if (dealerScore == getScore()) {
            return WinningResult.DRAW;
        }
        return WinningResult.LOSE;
    }
}