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

        if (dealerScore.isBlackJack() || playerScore.isBlackJack()) {
            processOfBlackjack(player, dealerScore, playerScore);
            return;
        }

        if (dealerScore.isBusted() || playerScore.isBusted()) {
            processOfBusted(player, dealerScore, playerScore);
            return;
        }

        processResult(player, dealerScore, playerScore);
    }

    private void processOfBlackjack(Player player, Score dealerScore, Score playerScore) {
        if (dealerScore.isBlackJack()) {
            if (playerScore.isBlackJack()) {
                PlayerResult playerResult = new PlayerResult(player, GameResultType.TIE, playerScore);
                playerResults.add(playerResult);
                return;
            }

            PlayerResult playerResult = new PlayerResult(player, GameResultType.LOSE, playerScore);
            playerResults.add(playerResult);
            return;
        }

        if (playerScore.isBlackJack()) {
            PlayerResult playerResult = new PlayerResult(player, GameResultType.WIN, playerScore);
            playerResults.add(playerResult);
        }
    }

    private void processOfBusted(Player player, Score dealerScore, Score playerScore) {
        boolean isDealerBusted = dealerScore.isBusted();
        boolean isPlayerBusted = playerScore.isBusted();

        if (isDealerBusted && isPlayerBusted) {
            PlayerResult playerResult = new PlayerResult(player, GameResultType.TIE, playerScore);
            playerResults.add(playerResult);
            return;
        }

        if (isDealerBusted) {
            PlayerResult playerResult = new PlayerResult(player, GameResultType.WIN, playerScore);
            playerResults.add(playerResult);
            return;
        }

        if (isPlayerBusted) {
            PlayerResult playerResult = new PlayerResult(player, GameResultType.LOSE, playerScore);
            playerResults.add(playerResult);
        }
    }

    private void processResult(Player player, Score dealerScore, Score playerScore) {
        int dealerScoreValue = dealerScore.getScoreValue();
        int playerScoreValue = playerScore.getScoreValue();

        GameResultType gameResultTypeOfPlayer = GameResultType.find(playerScoreValue, dealerScoreValue);

        PlayerResult playerResult = new PlayerResult(player, gameResultTypeOfPlayer, playerScore);
        playerResults.add(playerResult);
    }

    public PlayerResults getPlayerResults() {
        return playerResults;
    }

    public DealerResult getDealerResult() {
        return dealerResult;
    }
}
