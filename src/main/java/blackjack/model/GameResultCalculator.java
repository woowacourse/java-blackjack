package blackjack.model;

import java.util.List;

public class GameResultCalculator {

    public void calculate(List<GameSummary> gameSummaries) {
        GameSummary dealerGameSummary = gameSummaries.getFirst();
        Dealer dealer = (Dealer) dealerGameSummary.user();
        int dealerTotalScore = dealerGameSummary.totalScore();
        List<GameSummary> playerGameSummaries = gameSummaries.subList(1, gameSummaries.size());

        for (GameSummary playerGameSummary : playerGameSummaries) {
            Player player = (Player) playerGameSummary.user();
            int playerTotalScore = playerGameSummary.totalScore();
            if (playerTotalScore > 21) {
                player.mark(GameResult.LOSE);
                dealer.addResult(GameResult.WIN);
                continue;
            }

            if (dealerTotalScore > 21) {
                dealer.addResult(GameResult.LOSE);
                player.mark(GameResult.WIN);
                continue;
            }

            if (dealerTotalScore > playerTotalScore) {
                dealer.addResult(GameResult.WIN);
                player.mark(GameResult.LOSE);
                continue;
            }

            if (dealerTotalScore < playerTotalScore) {
                dealer.addResult(GameResult.LOSE);
                player.mark(GameResult.WIN);
                continue;
            }

            if (dealerTotalScore == playerTotalScore) {
                dealer.addResult(GameResult.DRAW);
            }
        }


    }
}
