package blackjack.domain.profit;

import blackjack.domain.game.Player;
import blackjack.domain.result.PlayerResult;
import blackjack.domain.result.PlayerResults;

public class BettingResult {
    private final DealerProfits dealerProfits;
    private final PlayerProfits playerProfits;

    public BettingResult(DealerProfits dealerProfits, PlayerProfits playerProfits) {
        this.dealerProfits = dealerProfits;
        this.playerProfits = playerProfits;
    }

    public void calculateAllResults(PlayerResults playerResults) {
        for (PlayerResult playerResult : playerResults.getPlayerResults()) {
            Player player = playerResult.getPlayer();
            int betAmount = player.getBetAmount();

            if (playerResult.isTie()) {
                saveProfitWhenPlayerTies(player);
                continue;
            }

            if (playerResult.isWinByBlackjack()) {
                saveProfitWhenPlayerIsBlackjack(player, betAmount);
                continue;
            }

            if (playerResult.isWinByNotBlackjack()) {
                saveProfitWhenPlayerWins(player, betAmount);
                continue;
            }

            if (playerResult.isLose()) {
                saveProfitWhenPlayerLoses(player, betAmount);
            }
        }
    }

    private void saveProfitWhenPlayerTies(Player player) {
        int playerProfitAmount = 0;

        PlayerProfit playerProfit = new PlayerProfit(player, playerProfitAmount);
        DealerProfit dealerProfit = DealerProfit.createWithPlayerProfit(playerProfitAmount);
        saveProfit(player, dealerProfit, playerProfit);
    }

    private void saveProfitWhenPlayerIsBlackjack(Player player, int betAmount) {
        int playerProfitAmount = (int) Math.round(betAmount * 1.5);

        PlayerProfit playerProfit = new PlayerProfit(player, playerProfitAmount);
        DealerProfit dealerProfit = DealerProfit.createWithPlayerProfit(playerProfitAmount);

        saveProfit(player, dealerProfit, playerProfit);
    }

    private void saveProfitWhenPlayerWins(Player player, int betAmount) {
        PlayerProfit playerProfit = new PlayerProfit(player, betAmount);
        DealerProfit dealerProfit = DealerProfit.createWithPlayerProfit(betAmount);

        saveProfit(player, dealerProfit, playerProfit);
    }

    private void saveProfitWhenPlayerLoses(Player player, int betAmount) {
        int playerProfitAmount = betAmount * -1;

        PlayerProfit playerProfit = new PlayerProfit(player, playerProfitAmount);
        DealerProfit dealerProfit = DealerProfit.createWithPlayerProfit(playerProfitAmount);

        saveProfit(player, dealerProfit, playerProfit);
    }

    private void saveProfit(Player player, DealerProfit dealerProfit, PlayerProfit playerProfit) {
        dealerProfits.add(player, dealerProfit);
        playerProfits.add(playerProfit);
    }

    public DealerProfits getDealerProfits() {
        return dealerProfits;
    }

    public PlayerProfits getPlayerProfits() {
        return playerProfits;
    }
}
