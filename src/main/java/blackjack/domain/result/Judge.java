package blackjack.domain.result;

import blackjack.domain.game.Dealer;
import blackjack.domain.game.Player;
import blackjack.domain.game.Players;

public class Judge {

    private final DealerResults dealerResults;
    private final PlayerResults playerResults;

    public Judge(DealerResults dealerResults, PlayerResults playerResults) {
        this.dealerResults = dealerResults;
        this.playerResults = playerResults;
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
                DealerResult dealerResult = new DealerResult(GameResultType.TIE, dealerScore);
                dealerResults.add(player, dealerResult);
                playerResults.add(playerResult);
                return;
            }

            PlayerResult playerResult = new PlayerResult(player, GameResultType.LOSE, playerScore);
            DealerResult dealerResult = new DealerResult(GameResultType.WIN, dealerScore);
            dealerResults.add(player, dealerResult);
            playerResults.add(playerResult);
            return;
        }

        if (playerScore.isBlackJack()) {
            PlayerResult playerResult = new PlayerResult(player, GameResultType.WIN, playerScore);
            DealerResult dealerResult = new DealerResult(GameResultType.LOSE, dealerScore);
            dealerResults.add(player, dealerResult);
            playerResults.add(playerResult);
        }
    }

    private void processOfBusted(Player player, Score dealerScore, Score playerScore) {
        boolean isDealerBusted = dealerScore.isBusted();
        boolean isPlayerBusted = playerScore.isBusted();

        if (isDealerBusted && isPlayerBusted) {
            PlayerResult playerResult = new PlayerResult(player, GameResultType.TIE, playerScore);
            DealerResult dealerResult = new DealerResult(GameResultType.TIE, dealerScore);
            dealerResults.add(player, dealerResult);
            playerResults.add(playerResult);
            return;
        }

        if (isDealerBusted) {
            PlayerResult playerResult = new PlayerResult(player, GameResultType.WIN, playerScore);
            DealerResult dealerResult = new DealerResult(GameResultType.LOSE, dealerScore);
            dealerResults.add(player, dealerResult);
            playerResults.add(playerResult);
            return;
        }

        if (isPlayerBusted) {
            PlayerResult playerResult = new PlayerResult(player, GameResultType.LOSE, playerScore);
            DealerResult dealerResult = new DealerResult(GameResultType.WIN, dealerScore);
            dealerResults.add(player, dealerResult);
            playerResults.add(playerResult);
            return;
        }
    }

    private void processResult(Player player, Score dealerScore, Score playerScore) {
        int dealerScoreValue = dealerScore.getScoreValue();
        int playerScoreValue = playerScore.getScoreValue();

        GameResultType gameResultTypeOfPlayer = GameResultType.find(playerScoreValue, dealerScoreValue);
        GameResultType gameResultTypeOfDealer = gameResultTypeOfPlayer.getOppositeType();

        DealerResult dealerResult = new DealerResult(gameResultTypeOfDealer, dealerScore);
        PlayerResult playerResult = new PlayerResult(player, gameResultTypeOfPlayer, playerScore);

        dealerResults.add(player, dealerResult);
        playerResults.add(playerResult);
    }

    public DealerResults getDealerResults() {
        return dealerResults;
    }

    public PlayerResults getPlayerResults() {
        return playerResults;
    }
}
