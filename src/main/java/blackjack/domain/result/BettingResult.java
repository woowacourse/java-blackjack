package blackjack.domain.result;

import blackjack.domain.game.Player;

public class BettingResult {
    private final DealerProfits dealerProfits;
    private final PlayerProfits playerProfits;

    public BettingResult(DealerProfits dealerProfits, PlayerProfits playerProfits) {
        this.dealerProfits = dealerProfits;
        this.playerProfits = playerProfits;
    }

    public void calculateAllResults(PlayerResults playerResults, DealerResults dealerResults) {
        for (PlayerResult playerResult : playerResults.getPlayerResults()) {
            Player player = playerResult.getPlayer();
            int betAmount = player.getBetAmount();

            if (playerResult.getGameResultType() == GameResultType.TIE) {
                PlayerProfit playerProfit = new PlayerProfit(player, 0);
                DealerProfit dealerProfit = new DealerProfit(0);
                dealerProfits.add(player, dealerProfit);
                playerProfits.add(playerProfit);
                return;
            }

            if (playerResult.getGameResultType() == GameResultType.WIN && playerResult.isBlackjack()) {
                int playerProfitAmount = (int) Math.round(betAmount * 1.5);

                PlayerProfit playerProfit = new PlayerProfit(player, playerProfitAmount);
                DealerProfit dealerProfit = new DealerProfit(playerProfitAmount * -1);

                dealerProfits.add(player, dealerProfit);
                playerProfits.add(playerProfit);
                return;
            }

            if (playerResult.getGameResultType() == GameResultType.WIN) {
                PlayerProfit playerProfit = new PlayerProfit(player, betAmount);
                DealerProfit dealerProfit = new DealerProfit(betAmount * -1);

                dealerProfits.add(player, dealerProfit);
                playerProfits.add(playerProfit);
                return;
            }

            if (playerResult.getGameResultType() == GameResultType.LOSE) {
                PlayerProfit playerProfit = new PlayerProfit(player, betAmount * -1);
                DealerProfit dealerProfit = new DealerProfit(betAmount);

                dealerProfits.add(player, dealerProfit);
                playerProfits.add(playerProfit);
                return;
            }
        }
    }

    public DealerProfits getDealerProfits() {
        return dealerProfits;
    }

    public PlayerProfits getPlayerProfits() {
        return playerProfits;
    }
}
