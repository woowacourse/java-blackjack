package domain.betting;

import domain.betting.manager.BettingPolicyManager;
import domain.gamer.Player;

public class BettingTable {

    private final Bettings bettings;
    private final BettingPolicyManager bettingManager;

    public BettingTable() {
        this.bettings = new Bettings();
        this.bettingManager = new BettingPolicyManager();
    }

    public void bet(Player player, Money bettingMoney) {
        bettings.bet(player, bettingMoney);
    }

    public Money getPlayerProfit(Player player) {
        return bettings.getPlayerBettingMoney(player);
    }

}
