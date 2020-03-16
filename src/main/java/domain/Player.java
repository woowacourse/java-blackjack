package domain;

import util.BlackJackRule;

public class Player extends User {
    private final Name name;
    private WinningResult winningResult;

    public Player(Name name) {
        this.name = name;
        this.winningResult = WinningResult.UNDEFINED;
        cards = new Cards();
    }

    @Override
    public boolean canHit() {
        if (cards.isBust()) {
            winningResult = WinningResult.LOSE;
            return false;
        }
        if (cards.isBlackJack()) {
            return false;
        }
        return true;
    }

    public WinningResult getWinningResult() {
        return winningResult;
    }

    public WinningResult calculateWinningResult(int dealerScore) {
        if (BlackJackRule.isBust(dealerScore) && winningResult == WinningResult.UNDEFINED) {
            winningResult = WinningResult.WIN;
        }
        if (winningResult == WinningResult.UNDEFINED) {
            winningResult = WinningResult.calculate(dealerScore, cards.getScore());
        }
        return winningResult;
    }

    public Name getName() {
        return name;
    }
}
