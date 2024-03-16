package blackjack.model.betting;

import blackjack.model.gameRule.Result;
import blackjack.model.gamer.Player;
import java.util.HashMap;
import java.util.Map;

public class BettingInfo {

    private final Map<Player, PlayerBetWallet> bettingInfo = new HashMap<>();

    public void addPlayerBetAmount(Player player, int betAmount) {
        PlayerBetWallet playerBetWallet = new PlayerBetWallet(betAmount);
        bettingInfo.put(player, playerBetWallet);
    }

    public void addPlayerProfitAmount(Player player, Result result) {
        PlayerBetWallet playerBetWallet = bettingInfo.get(player);
        playerBetWallet.registerProfitAmount(result);
    }

    public int calculatePlayerNetProfit(Player player) {
        PlayerBetWallet playerBetWallet = bettingInfo.get(player);
        return playerBetWallet.calculateNetProfit();
    }

    public int calculateDealerNetProfit() {
        int playersBetAmount = calculatePlayersBetAmount();
        int playersProfitAmount = calculatePlayersProfitAmount();
        return playersBetAmount - playersProfitAmount;
    }

    private int calculatePlayersBetAmount() {
        return bettingInfo.keySet().stream()
                .mapToInt(player -> bettingInfo.get(player).getBetAmount())
                .sum();
    }

    private int calculatePlayersProfitAmount() {
        return bettingInfo.keySet().stream()
                .mapToInt(player -> bettingInfo.get(player).getProfitAmount())
                .sum();
    }

    public Map<Player, PlayerBetWallet> getBettingInfo() {
        return bettingInfo;
    }
}
