package domain.participant;

import domain.BettingMoney;
import domain.GameResult;
import domain.HitThreshold;

public final class Player extends Participant {
    private BettingMoney bettingMoney;

    public Player(final String name) {
        super(HitThreshold.PLAYER_THRESHOLD, name);
    }

    public int getGameProfit(final Dealer dealer) {
        return GameResult.calculatePlayerProfit(this, dealer);
    }

    public void setBettingMoney(final BettingMoney battingMoney) {
        this.bettingMoney = battingMoney;
    }

    public int getBettingMoney() {
        return bettingMoney.getValue();
    }
}
