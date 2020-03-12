package domain.user;

import domain.WinningResult;
import util.BlackJackRule;

public class Player extends User {

    private final String name;
    private WinningResult winningResult = WinningResult.UNDEFINED;

    public Player(String name) {
        this.name = name;
    }

    @Override
    public boolean canDrawCard() {
        return !cards.isBust() && !cards.isBlackJack();
    }

    public void calculateWinningResult(int dealerScore) {
        if (cards.isBust()) {
            winningResult = WinningResult.LOSE;
            return;
        }
        if (BlackJackRule.isBust(dealerScore) && winningResult == WinningResult.UNDEFINED) {
            winningResult = WinningResult.WIN;
            return;
        }
        winningResult = WinningResult.calculate(dealerScore, cards.getScore());
    }

    public String getName() {
        return name;
    }

    public WinningResult getWinningResult() {
        return winningResult;
    }
}
