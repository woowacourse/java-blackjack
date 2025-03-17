package blackjack.domain.result;

import blackjack.domain.game.Dealer;
import blackjack.domain.game.Player;
import blackjack.domain.game.Players;

public class Judge {
    private final PlayerResults playerResults;
    private final DealerResult dealerResult;

    public Judge(PlayerResults playerResults, Dealer dealer) {
        this.playerResults = playerResults;
        this.dealerResult = new DealerResult(new Score(dealer));
    }

    public void calculateAllResults(Dealer dealer, Players players) {
        for (Player player : players.getPlayers()) {
            calculateResult(dealer, player);
        }
    }

    private void calculateResult(Dealer dealer, Player player) {
        Score dealerScore = new Score(dealer);
        Score playerScore = new Score(player);

        GameResult gameResult = playerScore.calculateGameResult(dealerScore);
        playerResults.add(new PlayerResult(player, gameResult, playerScore));
    }

    public PlayerResults getPlayerResults() {
        return playerResults;
    }

    public DealerResult getDealerResult() {
        return dealerResult;
    }
}
