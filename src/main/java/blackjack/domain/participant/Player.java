package blackjack.domain.participant;

import blackjack.domain.game.WinningResult;

public class Player extends Participant {

    private final int bettingMoney;
    private boolean isTurnEnd = false;

    public Player(String name) {
        super(name);
        this.bettingMoney = 0;
    }

    public Player(String name, int bettingMoney) {
        super(name);
        this.bettingMoney = bettingMoney;
    }

    public boolean canHit() {
        return !isBlackjack() && !isBust() && !isTurnEnd;
    }

    public void stay() {
        isTurnEnd = true;
    }

    public double calculateProfit(WinningResult winningResult) {
        if (isBlackjack()) {
            return bettingMoney * 1.5;
        }
        if (winningResult == WinningResult.WIN) {
            return bettingMoney;
        }
        if (winningResult == WinningResult.LOSE) {
            return -bettingMoney;
        }
        return 0;
    }
}
