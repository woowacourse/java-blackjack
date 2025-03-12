package domain.participant;

import domain.bet.BetMoney;
import domain.result.WinLossResult;

public class Player extends Participant {

    private final BetMoney betMoney;

    public Player(final String name) {
        super(name);
        this.betMoney = new BetMoney(0);
    }

    public Player(final String name, final double betMoney) {
        super(name);
        this.betMoney = new BetMoney(betMoney);
    }

    public Player bet(final double betAmount) {
        return new Player(getName(), betAmount);
    }

    public BetMoney computeBetResult(final WinLossResult winLossResult) {
        if(winLossResult == WinLossResult.WIN) {
            return betMoney.applyWinBonus();
        }
        if(winLossResult == WinLossResult.BLACKJACK_WIN) {
            return betMoney.applyBlackJackBonus();
        }
        if(winLossResult == WinLossResult.LOSS) {
            return betMoney.applyLossPenalty();
        }
        return betMoney;
    }

    public BetMoney getBetMoney() {
        return betMoney;
    }
}
