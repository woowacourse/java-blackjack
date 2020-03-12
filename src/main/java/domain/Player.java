package domain;

import util.BlackJackRule;

public class Player extends User {

    private final String name;
    private WinningResult winningResult;

    public Player(String name) {
        this.name = name;
        this.winningResult = WinningResult.UNDEFINED;
        cards = new Cards();
    }

    @Override
    public boolean isAbleDrawCards() {
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

    public void calculateWinningResult(int dealerScore) {
        if (BlackJackRule.isBust(dealerScore) && winningResult == WinningResult.UNDEFINED) {
            winningResult = WinningResult.WIN;
        }
        if (winningResult == WinningResult.UNDEFINED) {
            winningResult = WinningResult.calculate(dealerScore, cards.getScore());
        }
    }

    public String getName() {
        return name;
    }
}
