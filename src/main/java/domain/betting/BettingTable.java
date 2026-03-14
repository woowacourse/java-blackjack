package domain.betting;

import domain.betting.manager.BettingPolicyManager;
import domain.gamer.Dealer;
import domain.gamer.Player;
import domain.gamer.Players;

public class BettingTable {

    private final Bettings bettings;
    private final BettingPolicyManager bettingManager;

    public BettingTable() {
        this.bettings = new Bettings();
        this.bettingManager = new BettingPolicyManager();
    }

    public void bet(Player player, BettingMoney bettingMoney) {
        bettings.bet(player, bettingMoney);
    }

    public BettingMoney getBettingMoney(Player player) {
        return bettings.getPlayerBettingMoney(player);
    }

    public void applyBettingRate(Dealer dealer, Players players) {
        players.getPlayers().forEach(
                player -> progressBetting(dealer, player)
        );
    }

    private void progressBetting(Dealer dealer, Player player) {
        double bettingRate = bettingManager.gainBettingRate(dealer, player);
        bettings.settleBettingMoney(player, bettingRate);
    }

    public Profit getDealerProfit() {
        return bettings.calculateDealerProfit();
    }

    public Profit getPlayerProfit(Player player) {
        return bettings.getPlayerProfit(player);
    }
}
