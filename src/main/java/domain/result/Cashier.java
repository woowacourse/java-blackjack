package domain.result;

import domain.gamer.Dealer;
import domain.gamer.Player;
import domain.gamer.bet.GamersWallet;
import domain.gamer.bet.Money;

import java.util.List;
import java.util.Map;

public class Cashier {

    private final GamersWallet gamersWallet;
    private final Judge judge;

    public Cashier(GamersWallet gamersWallet, Judge judge) {
        this.gamersWallet = gamersWallet;
        this.judge = judge;
    }

    public void calculateBetResult(List<Player> players, Dealer dealer) {
        judge.decideResult(players, dealer);
        Map<Player, WinState> playersResult = judge.getPlayersResult();
        calculatePlayersProfit(playersResult);
        calculateDealerProfit(dealer);
    }

    private int calculateProfitByWinState(WinState winState, int money) {
        return (int) (money * winState.getProfitRate());
    }

    private void calculatePlayersProfit(Map<Player, WinState> playersResult) {
        for (Player player : playersResult.keySet()) {
            int playerMoney = gamersWallet.findMoneyByPlayer(player);
            playerMoney = calculateProfitByWinState(playersResult.get(player), playerMoney);
            gamersWallet.applyProfitToGamer(player, new Money(playerMoney));
        }
    }

    private void calculateDealerProfit(Dealer dealer) {
        int allProfit = gamersWallet.sumAllPlayerProfit();
        gamersWallet.applyProfitToGamer(dealer, new Money(-allProfit));
    }

    public GamersWallet getGamersWallet() {
        return gamersWallet;
    }
}
