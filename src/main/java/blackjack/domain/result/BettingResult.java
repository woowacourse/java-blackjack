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
            DealerResult dealerResult = dealerResults.findResultByPlayer(playerResult.getPlayer());
            Player player = playerResult.getPlayer();

            if (playerResult.getGameResultType() == GameResultType.TIE) {
                PlayerProfit playerProfit = new PlayerProfit(player, 0);
                DealerProfit dealerProfit = new DealerProfit(0);
                dealerProfits.add(player, dealerProfit);
                playerProfits.add(playerProfit);
                return;
            }

            if (playerResult.getGameResultType() == GameResultType.WIN && playerResult.isBlackjack()) {
                int betAmount = player.getBetAmount();
                int playerProfitAmount = (int) Math.round(betAmount * 1.5);

                PlayerProfit playerProfit = new PlayerProfit(player, playerProfitAmount);
                DealerProfit dealerProfit = new DealerProfit(playerProfitAmount * -1);

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
